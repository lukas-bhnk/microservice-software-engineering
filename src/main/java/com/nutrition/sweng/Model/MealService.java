package com.nutrition.sweng.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MealService {
    private MealRepository mealRepository;
    private FoodRepository foodRepository;

    @Autowired
    public MealService(MealRepository mealRepository,FoodRepository foodRepository){
        this.mealRepository = mealRepository;
        this.foodRepository = foodRepository;
    }

    public Meal getMeal(int id){
        Optional<Meal> mealOptional = mealRepository.findById(id);
        if(mealOptional.isPresent()){
            Meal meal = mealOptional.get();
            return meal;
        }
        else{
            throw new ResourceNotFoundException("This meal ist not in the Database");
        }
    }
}
