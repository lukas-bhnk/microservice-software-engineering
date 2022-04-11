package com.nutrition.sweng.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MealService {
    private MealRepository mealRepository;

    @Autowired
    public MealService(MealRepository mealRepository){
        this.mealRepository = mealRepository;
    }

    public Meal getMeal(Integer id){
        Optional<Meal> mealOptional = mealRepository.findById(id);
        if(mealOptional.isPresent()){
            Meal meal = mealOptional.get();
            System.out.println(meal);
            return meal;
        }
        else{
            throw new ResourceNotFoundException("This meal ist not in the Database");
        }
    }
}
