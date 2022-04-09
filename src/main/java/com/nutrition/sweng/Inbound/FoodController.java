package com.nutrition.sweng.Inbound;

import com.nutrition.sweng.Model.Food;
import com.nutrition.sweng.Model.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
