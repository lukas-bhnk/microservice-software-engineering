package com.nutrition.sweng.Service;

import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Repository.FoodRepository;
import com.nutrition.sweng.Repository.MineralsRepository;
import com.nutrition.sweng.Repository.NutritionalValuesRepository;
import com.nutrition.sweng.Repository.VitaminsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FoodService {
    private FoodRepository foodRepository;
    private MineralsRepository mineralsRepository;
    private VitaminsRepository vitaminsRepository;
    private NutritionalValuesRepository nutritionalValuesRepository;
    public static final String NO_FOOD_INFO = "No Food Info Available.";
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    public FoodService(FoodRepository foodRepository, NutritionalValuesRepository nutritionalValuesRepository, VitaminsRepository vitaminsRepository, MineralsRepository mineralsRepository){
        this.foodRepository = foodRepository;
        this.mineralsRepository = mineralsRepository;
        this.nutritionalValuesRepository = nutritionalValuesRepository;
        this.vitaminsRepository = vitaminsRepository;
    }

    public Food getFood(long id){
        Optional<Food> foodOptional = foodRepository.findById(id);
        if(foodOptional.isPresent()){
            Food food = foodOptional.get();
            LOG.info("Getting successfully food with id {}", id);
            return food;
        }
        else{
            LOG.error("Getting food failed, food is not in the Database");
            throw new ResourceNotFoundException("This food is not in the Database");
        }
    }

    public List<Food> getFood(String name){
        List<Food> foodList = foodRepository.findByName(name);
        if(foodList.isEmpty()) {
            LOG.error("No food for this name was found");
            throw new ResourceNotFoundException("This name is not in the Database");
        }
        LOG.info("Successfully found food with name {}", name);
        return foodList;
    }


    public void saveAllFoodValues(Food food, Minerals minerals, Vitamins vitamins, NutritionalValues nutritionalValues){
        food.setNutritionalValues(nutritionalValues);
        food.setMinerals(minerals);
        food.setVitamins(vitamins);
        food.setFoodEntries(new HashSet<FoodEntry>());
        vitamins.setFood(food);
        minerals.setFood(food);
        nutritionalValues.setFood(food);
        foodRepository.save(food);
        vitaminsRepository.save(vitamins);
        mineralsRepository.save(minerals);
        nutritionalValuesRepository.save(nutritionalValues);
        LOG.info("Successfully saved all food values");
    }
}
