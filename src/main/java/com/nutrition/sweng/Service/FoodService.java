package com.nutrition.sweng.Service;

import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Repository.FoodRepository;
import com.nutrition.sweng.Repository.MineralsRepository;
import com.nutrition.sweng.Repository.NutritionalValuesRepository;
import com.nutrition.sweng.Repository.VitaminsRepository;
import feign.RetryableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FoodService {
    private FoodRepository foodRepository;
    private MineralsRepository mineralsRepository;
    private VitaminsRepository vitaminsRepository;
    private NutritionalValuesRepository nutritionalValuesRepository;
    private FoodInfoServiceClient foodInfoServiceClient;
    public static final String NO_FOOD_INFO = "No Food Info Available.";
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    public FoodService(FoodRepository foodRepository, NutritionalValuesRepository nutritionalValuesRepository, VitaminsRepository vitaminsRepository, MineralsRepository mineralsRepository, FoodInfoServiceClient foodInfoServiceClient){
        this.foodRepository = foodRepository;
        this.mineralsRepository = mineralsRepository;
        this.nutritionalValuesRepository = nutritionalValuesRepository;
        this.vitaminsRepository = vitaminsRepository;
        this.foodInfoServiceClient = foodInfoServiceClient;
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

    public List<Food> getFood(String name){
        List<Food> foodList = foodRepository.findByName(name);
        return foodList;
    }

    public String getInfo(long id){
        String info = this.getAllFoodInfos(id);
        return info;
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
    }


    @Retryable(include = RetryableException.class,
            maxAttempts = 3, //first attempt and 2 retries
            backoff=@Backoff(delay=100, maxDelay=500))
    public String getAllFoodInfos(Long id) {
        LOG.info("Execute get all Food Infos({}).", ("Food: " + id));
        String info = foodInfoServiceClient.getFood(String.valueOf(id)).toString() + "\r\n" +
                foodInfoServiceClient.getMinerals(String.valueOf(id)).toString() + "\r\n" +
                foodInfoServiceClient.getNutritionalValues(String.valueOf(id)).toString() + "\r\n" +
                foodInfoServiceClient.getVitamins(String.valueOf(id)).toString();
        return info;
    }

    @Recover
    public String fallBackPrice(RetryableException e) {
        LOG.error("Problem occured when calling food information service. Use fallback! ", e);
        return NO_FOOD_INFO;
    }
}
