package com.nutrition.sweng.Service;

import com.nutrition.sweng.Model.AlreadyExistException;
import com.nutrition.sweng.Model.Food;
import com.nutrition.sweng.Model.Meal;
import com.nutrition.sweng.Model.ResourceNotFoundException;
import com.nutrition.sweng.Repository.FoodRepository;
import com.nutrition.sweng.Repository.MealRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class MealService {
    private FoodRepository foodRepositroy;
    private MealRepository mealRepository;
    private final Logger LOG =  LoggerFactory.getLogger(getClass());

    @Autowired
    public MealService(MealRepository mealRepository, FoodRepository foodRepository){
        this.mealRepository = mealRepository;
        this.foodRepositroy = foodRepository;
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

/*    public Food addFood(Long mealId, Long foodId, Integer quantityInMGorML) {
        //food Multiplicator to multiply the Food values with the quantity (the Food is saved with values per 100g or 100ml)
        double foodMultiplicator = quantityInMGorML / 100;
        LOG.info("Execute addFood(MealId: {}, FoodId: {}, Quantity: {}).", mealId, foodId, quantityInMGorML);
        Optional<Meal> mealOptional = mealRepository.findById(mealId);
        if (mealOptional.isPresent()) {
            Meal meal = mealOptional.get();
            Set<Food> foodSet = meal.getFoodList();
            for (Food f : foodSet) {
                if (f.getId() == foodId) {
                    LOG.error("Adding product to Meal failed. Food {} already exist", f.getName());
                    throw new AlreadyExistException("Product already exists");
                }
            }
        }
        else {
            throw new ResourceNotFoundException("Requested Food is not in DB");
        }
    }*/
}
