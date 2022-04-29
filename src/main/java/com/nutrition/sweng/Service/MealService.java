package com.nutrition.sweng.Service;

import com.nutrition.sweng.Model.Meal;
import com.nutrition.sweng.Model.ResourceNotFoundException;
import com.nutrition.sweng.Repository.FoodRepository;
import com.nutrition.sweng.Repository.MealRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MealService {
    private MealRepository mealRepository;
    private final Logger LOG =  LoggerFactory.getLogger(getClass());

    @Autowired
    public MealService(MealRepository mealRepository, FoodRepository foodRepository){
        this.mealRepository = mealRepository;
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
}
