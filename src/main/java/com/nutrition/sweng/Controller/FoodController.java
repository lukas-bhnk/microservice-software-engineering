package com.nutrition.sweng.Controller;

import com.nutrition.sweng.DTO.FoodDto;
import com.nutrition.sweng.Model.Food;
import com.nutrition.sweng.Service.FoodInfoServiceClient;
import com.nutrition.sweng.Service.FoodService;
import feign.RetryableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Backoff;

@RestController
@RequestMapping("rest/food")
public class FoodController {
    private FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService){
        this.foodService = foodService;
    }

    @GetMapping("/{id}")
    public FoodDto getFood(@PathVariable Long id){
        Food food = this.foodService.getFood(id);
        return new FoodDto(food);
    }

    @GetMapping("getInfo/{id}")
    public String getInfo(@PathVariable Long id){
        String info = this.foodService.getInfo(id);
        return info;
    }


}
