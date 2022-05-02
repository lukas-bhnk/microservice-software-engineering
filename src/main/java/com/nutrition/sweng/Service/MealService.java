package com.nutrition.sweng.Service;

import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Repository.FoodRepository;
import com.nutrition.sweng.Repository.MealRepository;
import feign.RetryableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class MealService {
    private FoodRepository foodRepository;
    private MealRepository mealRepository;
    private FoodInfoServiceClient foodInfoServiceClient;
    private final Logger LOG =  LoggerFactory.getLogger(getClass());

    @Autowired
    public MealService(MealRepository mealRepository, FoodRepository foodRepository, FoodInfoServiceClient foodInfoServiceClient){
        this.mealRepository = mealRepository;
        this.foodRepository = foodRepository;
        this.foodInfoServiceClient = foodInfoServiceClient;
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
     * Find a certain Meal in the database.
     * @param mealId Id of the Meal, which should be added by the food
     * @param foodId Id of the Food that should be added into the meal
     * @param quantityInMGorML Quantity of the food, that schould be added into the meal
     * @return a Meal
     */
    public Meal addFood(Long mealId, Long foodId, Integer quantityInMGorML) {
        //food Multiplicator to multiply the Food values with the quantity (the Food is saved with values per 100g or 100ml)
        double foodMultiplicator = quantityInMGorML / 100;
        LOG.info("Execute addFood(MealId: {}, FoodId: {}, Quantity: {}).", mealId, foodId, quantityInMGorML);
        Optional<Meal> mealOptional = mealRepository.findById(mealId);
        if (mealOptional.isPresent()) {
            Meal meal = mealOptional.get();
            Set<Food> foodSet = meal.getFoodList();
            for (Food f : foodSet) {
                if (f.getId() == foodId) {
                    LOG.error("Adding food to Meal failed. Food {} already exists in Meal", f.getName());
                    throw new AlreadyExistException("Food already exists in Meal");
                }
            }
            Optional<Food> foodOptional = foodRepository.findById(foodId);
            if (foodOptional.isPresent()){
                Food food = foodOptional.get();
                NutritionalValues nutritionalValues = this.foodInfoServiceClient.getNutritionalValues(String.valueOf(foodId));
                Integer calories = meal.getCalories() + (int)Math.ceil(nutritionalValues.getCalories() * foodMultiplicator);
                Double carbs = meal.getCarbs() + nutritionalValues.getCarbs() * foodMultiplicator;
                Double fats = meal.getFats() + nutritionalValues.getFats() * foodMultiplicator;
                Double proteins = meal.getProteins() + nutritionalValues.getProteins() * foodMultiplicator;
                meal.setCalories(calories);
                meal.setCarbs(carbs);
                meal.setProteins(proteins);
                meal.setFats(fats);
                foodSet.add(food);
                mealRepository.save(meal);
                LOG.info("Adding food to meal successful. New food added with name: {}", food.getName());
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
