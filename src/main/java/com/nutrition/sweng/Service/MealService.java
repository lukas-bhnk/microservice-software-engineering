package com.nutrition.sweng.Service;

import com.nutrition.sweng.Event.EventPublisher;
import com.nutrition.sweng.Event.MealAddedEvent;
import com.nutrition.sweng.Event.MealChangedEvent;
import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Repository.FoodEntryRepository;
import com.nutrition.sweng.Repository.FoodRepository;
import com.nutrition.sweng.Repository.MealRepository;
import com.nutrition.sweng.Repository.UserRepository;
import feign.RetryableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MealService {
    private MealRepository mealRepository;
    private FoodRepository foodRepository;
    private FoodEntryRepository foodEntryRepository;
    private FoodInfoServiceClient foodInfoServiceClient;
    private UserRepository userRepository;
    private EventPublisher eventPublisher;
    private final Logger LOG =  LoggerFactory.getLogger(getClass());

    @Autowired
    public MealService(MealRepository mealRepository, FoodInfoServiceClient foodInfoServiceClient, FoodRepository foodRepository, FoodEntryRepository foodEntryRepository, UserRepository userRepository, EventPublisher eventPublisher){
        this.mealRepository = mealRepository;
        this.foodRepository = foodRepository;
        this.foodInfoServiceClient = foodInfoServiceClient;
        this.foodEntryRepository = foodEntryRepository;
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    /**
     * Find a certain Meal in the database.
     * @param id of the requested Meal
     * @return a Meal
     */
    public Meal getMeal(Long id){
        LOG.info("Execute getMeal({}).", id);
        Optional<Meal> mealOptional = mealRepository.findById(id);
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
        List<Meal> meals = mealRepository.findByDateAndUser(date, user.getId());
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
     * @return a Meal
     */
    public Meal deleteFood(Long mealId, Long foodId){
        LOG.info("Execute deleteFood(MealId: {}, FoodId: {}).", mealId, foodId);
        Optional<Meal> mealOptional = mealRepository.findById(mealId);
        if (mealOptional.isPresent()) {
            Meal meal = mealOptional.get();
            Set<FoodEntry> foodEntries = meal.getFoodEntries();
            for (FoodEntry foodEntry : foodEntries) {
                Food f = foodEntry.getFood();
                if (f.getId() == foodId) {
                    foodEntries.remove(foodEntry);
                    foodEntryRepository.delete(foodEntry);
                    meal = calculateNutritionalValuesInMeal(meal);
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
        if (!userOptional.isPresent()) {
            LOG.error("User is not in DB");
            throw new ResourceNotFoundException("User is not in DB");
        }
        User user = userOptional.get();
        Meal meal = new Meal();
        meal.setDate(date);
        meal.setProteins(0.0);
        meal.setCarbs(0.0);
        meal.setFats(0.0);
        meal.setCalories(0);
        meal.setUserFk(user);
        meal.setFoodEntries(new HashSet<FoodEntry>());
        meal.setMealCategory(mealCategory);
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
    public Meal addFood(Long mealId, Long foodId, Integer quantityInGorML) {

        LOG.info("Execute addFood(MealId: {}, FoodId: {}, Quantity: {}).", mealId, foodId, quantityInGorML);
        Optional<Meal> mealOptional = mealRepository.findById(mealId);
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
                foodEntry.setFood(food);
                foodEntry.setMeal(meal);
                foodEntry = this.calculateNutritionalValuesInFoodEntry(foodEntry, foodId, quantityInGorML);
                foodEntries.add(foodEntry);
                foodEntryRepository.save(foodEntry);
                LOG.info("Adding Foodentry to meal successful. New food added with name: {}", food.getName());
                var event = new MealChangedEvent(meal);
                var published = this.eventPublisher.publishEvent(event);
                if (!published) {
                    //TODO: we have to rollback the transaction
                }
                return meal;
            }
            else {
                throw new ResourceNotFoundException("Requested Food is not in DB");
            }
        }
        else {
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
    public Meal updateQuantity(Long mealId, Long foodId, int quantityInGorML ){
        LOG.info("Execute updateQuantityOfAFoodEntry(MealId: {}, FoodId: {}, Quantity: {}).", mealId, foodId, quantityInGorML);
        Optional<Meal> mealOptional = mealRepository.findById(mealId);
        if (mealOptional.isPresent()) {
            Meal meal = mealOptional.get();
            Set<FoodEntry> foodEntries = meal.getFoodEntries();
            for (FoodEntry foodEntry : foodEntries) {
                Food f = foodEntry.getFood();
                if (f.getId() == foodId) {
                    foodEntry = this.calculateNutritionalValuesInFoodEntry(foodEntry, foodId,quantityInGorML);
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
        double foodMultiplicator = quantityInGorML / 100;
        NutritionalValues nutritionalValues = this.foodInfoServiceClient.getNutritionalValues(String.valueOf(foodId));
        int calories = nutritionalValues.getCalories() + (int)Math.ceil(nutritionalValues.getCalories() * foodMultiplicator);
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
            fats += foodEntry.getFats();
        }
        meal.setCalories(calories);
        meal.setFats(fats);
        meal.setCarbs(carbs);
        meal.setProteins(proteins);
        return meal;
    }

    @Retryable(include = RetryableException.class,
            maxAttempts = 3, //first attempt and 2 retries
            backoff=@Backoff(delay=100, maxDelay=500))
    public NutritionalValues getNutritionalValues(Long id) {
        LOG.info("Execute get Nutritional Values({}).", ("Food: " + id));
        return this.foodInfoServiceClient.getNutritionalValues(String.valueOf(id));
    }

    @Recover
    public void fallBackNutritionalValues(RetryableException e) {
        LOG.error("Problem occured when calling food information service. Use fallback! ", e);
        throw new ResourceNotFoundException("These Nutritional Values are not in the DB");
    }
}
