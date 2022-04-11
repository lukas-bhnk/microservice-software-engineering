package com.nutrition.sweng.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodService {
    private FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository){
        this.foodRepository = foodRepository;
    }

    public Food getFood(long id){
        Optional<Food> foodOptional = foodRepository.findById(id);
        if(foodOptional.isPresent()){
            Food food = foodOptional.get();
            return food;
        }
        else{
            throw new ResourceNotFoundException("This food ist not in the Database");
        }
    }
}
