package com.nutrition.sweng.Service;

import com.nutrition.sweng.Event.EventPublisher;
import com.nutrition.sweng.Event.MealAddedEvent;
import com.nutrition.sweng.Event.MealChangedEvent;
import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Repository.*;
import feign.RetryableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class MealService {
    private MealRepository mealRepository;
    private FoodRepository foodRepository;
    private NutritionalValuesRepository nutritionalValuesRepository;
    private FoodEntryRepository foodEntryRepository;
    private JokeServiceClient jokeServiceClient;
    private UserRepository userRepository;
    private EventPublisher eventPublisher;
    public static final String NO_JOKE = "No Joke Available.";
    private final Logger LOG =  LoggerFactory.getLogger(getClass());

    @Autowired
    public MealService(MealRepository mealRepository, JokeServiceClient jokeServiceClient, NutritionalValuesRepository nutritionalValuesRepository, FoodRepository foodRepository, FoodEntryRepository foodEntryRepository, UserRepository userRepository, EventPublisher eventPublisher){
        this.mealRepository = mealRepository;
        this.foodRepository = foodRepository;
        this.nutritionalValuesRepository = nutritionalValuesRepository;
        this.jokeServiceClient = jokeServiceClient;
        this.foodEntryRepository = foodEntryRepository;
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    /**
     * Find a certain Meal in the database.
     * @param id of the requested Meal
     * @return a Meal
     */
    public Meal getMeal(Long id, String email){
        LOG.info("Execute getMeal({}, {}).", id, email);
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            LOG.error("User is not in DB");
            throw new ResourceNotFoundException("User is not in DB");
        }
        User user = userOptional.get();
        Optional<Meal> mealOptional = mealRepository.findByIdAndUser(id, user);
        if (mealOptional.isPresent()) {
            Meal meal = mealOptional.get();
            LOG.info("Getting Meal successful with ID {}", meal.getId());
            return meal;
        } else {
            LOG.error("Getting Meal failed. Meal doesnt exist");
            throw new ResourceNotFoundException("Requested Meal is not in DB");
        }
    }

    /**
     * Find all meals of a specific day and user in the database.
     * @param date of the requested Meal
     * @param email of the user to find the user
     * @return meals list of all meals of the day and the user
     */
    public List<Meal> getDailyMeals(Date date, String email){
        LOG.info("Execute getDailyMeals({}, {}).", date, email);
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            LOG.error("User is not in DB");
            throw new ResourceNotFoundException("User is not in DB");
        }
        User user = userOptional.get();
        List<Meal> meals = mealRepository.findByDateAndUser(date, user);
        if(meals.isEmpty()){
            LOG.error("Requested Meals({}, {})are not in the DB", date, email);
            throw new ResourceNotFoundException("Requested Meals are not in the DB");
        }
        return meals;
    }

    /**
     * Delete a certain Food of a certain Meal in the database.
     * @param mealId Id of the Meal, which should delete the food
     * @param foodId Id of the Food that should be delete of the meal
     * @param email of the User
     * @return a Meal
     */
    public Meal deleteFood(Long mealId, Long foodId, String email){
        LOG.info("Execute deleteFood(MealId: {}, FoodId: {}, Email: {}).", mealId, foodId, email);
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            LOG.error("User is not in DB");
            throw new ResourceNotFoundException("User is not in DB");
        }
        User user = userOptional.get();
        Optional<Meal> mealOptional = mealRepository.findByIdAndUser(mealId, user);
        if (mealOptional.isPresent()) {
            Meal meal = mealOptional.get();
            Set<FoodEntry> foodEntries = meal.getFoodEntries();
            for (FoodEntry foodEntry : foodEntries) {
                Food f = foodEntry.getFood();
                if (f.getId() == foodId) {
                    f.getFoodEntries().remove(foodEntry);
                    foodEntries.remove(foodEntry);
                    meal.setFoodEntries(foodEntries);
                    foodEntry.setMeal(null);
                    foodEntry.setFood(null);
                    foodEntryRepository.delete(foodEntry);
                    meal = calculateNutritionalValuesInMeal(meal);
                    mealRepository.save(meal);
                    LOG.info("Deleting food from meal successful. Food deleted with name: {}", f.getName());
                    var event = new MealChangedEvent(meal);
                    var published = this.eventPublisher.publishEvent(event);
                    if (!published) {
                        //TODO: we have to rollback the transaction
                    }
                    return meal;
                }
            }
            LOG.error("Deleting food from Meal failed. Food {} not exists in Meal", foodId);
            throw new ResourceNotFoundException("Food not exists in Meal");
        }
       else {
            LOG.error("Meal {} not exists in DB", mealId);
            throw new ResourceNotFoundException("Meal not exists in DB");
        }
    }
    /**
     * Create a new Meal and save it do the database.
     * @param date of the Meal
     * @param mealCategory Breakfast, Lunch, Dinner, Snacks
     * @param email find the user by his email and add it to the meal
     * @return a Meal
     */
    public Meal createMeal(Date date, MealCategory mealCategory, String email){
        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = null;
        if (!userOptional.isPresent()) {
            LOG.info("User is not in DB, created a new User");
            user = new User(email);
            userRepository.save(user);
        }
        else user = userOptional.get();
        Meal meal = new Meal();
        meal.setDate(date);
        meal.setMealCategory(mealCategory);
        meal.setProteins(0.0);
        meal.setCarbs(0.0);
        meal.setFats(0.0);
        meal.setCalories(0);
        meal.setUserFk(user);
        meal.setFoodEntries(new HashSet<FoodEntry>());
        mealRepository.save(meal);
        LOG.info("Creating meal successful. New food created with Date: {} and Category: {}", meal.getDate(), meal.getMealCategory().name());
        var event = new MealAddedEvent(meal);
        var published = this.eventPublisher.publishEvent(event);
        if (!published) {
            //TODO: we have to rollback the transaction
        }

        return meal;

    }

    /**
     * Add a food and the quantity to a certain Meal in the database.
     * @param mealId Id of the Meal, which should be added by the food
     * @param foodId Id of the Food that should be added into the meal
     * @param quantityInGorML Quantity of the food (Grams or Millilitre), that should be added into the meal
     * @return a Meal
     */
    public Meal addFood(Long mealId, Long foodId, Integer quantityInGorML, String email) {

        LOG.info("Execute addFood(MealId: {}, FoodId: {}, Quantity: {}).", mealId, foodId, quantityInGorML);
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            LOG.error("User is not in DB");
            throw new ResourceNotFoundException("User is not in DB");
        }
        User user = userOptional.get();
        Optional<Meal> mealOptional = mealRepository.findByIdAndUser(mealId, user);
        if (mealOptional.isPresent()) {
            Meal meal = mealOptional.get();
            Set<FoodEntry> foodEntries = meal.getFoodEntries();
            for (FoodEntry foodEntry : foodEntries) {
                Food f = foodEntry.getFood();
                if (f.getId() == foodId) {
                    LOG.error("Adding food to Meal failed. Food {} already exists in Meal", f.getName());
                    throw new AlreadyExistException("Food already exists in Meal");
                }
            }
            Optional<Food> foodOptional = foodRepository.findById(foodId);
            if (foodOptional.isPresent()){
                Food food = foodOptional.get();
                FoodEntry foodEntry = new FoodEntry();
                foodEntry = this.calculateNutritionalValuesInFoodEntry(foodEntry, foodId, quantityInGorML);
                foodEntry.setFood(food);
                foodEntry.setMeal(meal);
                foodEntries.add(foodEntry);
                meal = this.calculateNutritionalValuesInMeal(meal);
                foodEntryRepository.save(foodEntry);
                mealRepository.save(meal);
                LOG.info("Adding Foodentry to meal successful. New food added with name: {}", food.getName());
                var event = new MealChangedEvent(meal);
                var published = this.eventPublisher.publishEvent(event);
                if (!published) {
                    //TODO: we have to rollback the transaction
                }
                return meal;
            }
            else {
                LOG.error("Adding food to Meal failed. Food {} not exists in DB", foodId);
                throw new ResourceNotFoundException("Requested Food is not in DB");
            }
        }
        else {
            LOG.error("Adding food to Meal failed. Meal {} not exists in DB", mealId);
            throw new ResourceNotFoundException("Requested Meal is not in DB");
        }
    }
    /**
     * Update the quantity of a foodEntry from a certain Meal in the database.
     * @param mealId Id of the Meal, which should be updated
     * @param foodId Id of the Food that should be updated in the meal
     * @param quantityInGorML Quantity of the food (Grams or Millilitre), that should be updated in the meal
     * @return a Meal
     */
    public Meal updateQuantity(Long mealId, Long foodId, int quantityInGorML, String email ){
        LOG.info("Execute updateQuantityOfAFoodEntry(MealId: {}, FoodId: {}, Quantity: {}).", mealId, foodId, quantityInGorML);
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            LOG.error("User is not in DB");
            throw new ResourceNotFoundException("User is not in DB");
        }
        User user = userOptional.get();
        Optional<Meal> mealOptional = mealRepository.findByIdAndUser(mealId, user);
        if (mealOptional.isPresent()) {
            Meal meal = mealOptional.get();
            Set<FoodEntry> foodEntries = meal.getFoodEntries();
            for (FoodEntry foodEntry : foodEntries) {
                Food f = foodEntry.getFood();
                if (f.getId() == foodId) {
                    foodEntries.remove(foodEntry);
                    foodEntry = this.calculateNutritionalValuesInFoodEntry(foodEntry, foodId, quantityInGorML);
                    foodEntries.add(foodEntry);
                    meal = this.calculateNutritionalValuesInMeal(meal);
                    foodEntryRepository.save(foodEntry);
                    mealRepository.save(meal);
                    var event = new MealChangedEvent(meal);
                    var published = this.eventPublisher.publishEvent(event);
                    if (!published) {
                        //TODO: we have to rollback the transaction
                    }
                    return meal;
                }
            }
            LOG.error("Updating quantity of Meal failed. Food {} does not exist in Meal", foodId);
            throw new ResourceNotFoundException("Food does not exist in Meal");
            }
        else {
            throw new ResourceNotFoundException("Requested Meal is not in DB");
        }
    }
    /**
     * Helpmethod for updating and adding a FoodEntry in a meal
     * It calculates for a specific FoodEntry the Nutritonal Values on the basics of the NutritionalValues from the food
     * @param foodEntry the FoodEntry, which should calculate the NutritionalValues
     * @param foodId the Food, on basic of the NutritionalValues of food
     * @param quantityInGorML int quantity in grams or millilitres
     * @return FoodEntry
     */
    public FoodEntry calculateNutritionalValuesInFoodEntry(FoodEntry foodEntry, Long foodId, int quantityInGorML){
        //food Multiplicator to multiply the Food values with the quantity (the Food is saved with values per 100g or 100ml)
        double foodMultiplicator = quantityInGorML / 100.0;
        Optional<NutritionalValues> nutritionalValuesOptional = this.nutritionalValuesRepository.findById(foodId);
        if (!nutritionalValuesOptional.isPresent()) {
            LOG.error("Updating quantity of Meal failed. Food {} does not exist in Meal", foodId);
            throw new ResourceNotFoundException("Food does not exist in Meal");
        }
        NutritionalValues nutritionalValues = nutritionalValuesOptional.get();
        int calories = (int)Math.ceil(nutritionalValues.getCalories() * foodMultiplicator);
        System.out.println(calories);
        Double carbs = nutritionalValues.getCarbs() * foodMultiplicator;
        Double fats = nutritionalValues.getFats() * foodMultiplicator;
        Double proteins = nutritionalValues.getProteins() * foodMultiplicator;
        foodEntry.setCalories(calories);
        foodEntry.setFats(fats);
        foodEntry.setCarbs(carbs);
        foodEntry.setProteins(proteins);
        foodEntry.setQuantity(quantityInGorML);
        return foodEntry;
    }

    /**
     * Helpmethod for deleting, updating and adding a FoodEntry to a meal
     * It calculates for all foodEntries the sum of calories, fats, carbs, proteins and set these sums in the Meal
     * @param meal the Meal, which should calculate the NutritionalValues
     * @return meal
     */
    public Meal calculateNutritionalValuesInMeal(Meal meal){
        Set<FoodEntry> foodEntries = meal.getFoodEntries();
        int calories = 0;
        double fats = 0.0;
        double carbs = 0.0;
        double proteins = 0.0;
        for (FoodEntry foodEntry : foodEntries){
            calories += foodEntry.getCalories();
            fats += foodEntry.getFats();
            carbs += foodEntry.getCarbs();
            proteins += foodEntry.getProteins();
        }
        meal.setCalories(calories);
        meal.setFats(fats);
        meal.setCarbs(carbs);
        meal.setProteins(proteins);
        return meal;
    }

    public String getJoke(String category){
        String joke = this.queryJoke(category);
        return joke;
    }

    /**
     * This method is used to query a joke by a specific category.
     * Uses the pricingServiceClient to send an external request.
     * @param category
     * @return joke for this category
     */
    @Retryable(include = RetryableException.class,
            maxAttempts = 3, //first attempt and 2 retries
            backoff=@Backoff(delay=100, maxDelay=500))
    public String queryJoke(String category) {
        LOG.info("Execute query Joke({}).", category);
        Joke joke = this.jokeServiceClient.getJoke(category);
        return joke.getJoke();

    }

    @Recover
    public String fallBackJoke(RetryableException e) {
        LOG.error("Problem occured when calling joke service. Use fallback! ", e);
        return NO_JOKE;
    }
}
