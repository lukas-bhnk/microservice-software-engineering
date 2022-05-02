package com.nutrition.sweng.Controller;

import com.nutrition.sweng.DTO.MealDto;
import com.nutrition.sweng.Model.Meal;
import com.nutrition.sweng.Service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest/meal")
public class MealController {

    private MealService mealService;

    @Autowired
    public MealController(MealService mealService){
        this.mealService = mealService;
    }

    @GetMapping("/{id}")
    public MealDto getMeal(@PathVariable Long id){
        Meal meal = this.mealService.getMeal(id);
        return new MealDto(meal);
    }

    //@PostMapping("/?mealId={mealId}/?foodId={foodId}?quantity={quantity}")
    @PostMapping("/{mealId}/{foodId}/{quantity}")
    public MealDto addFood(@PathVariable Long mealId, @PathVariable Long foodId, @PathVariable Integer quantity){
        Meal meal = this.mealService.addFood(mealId, foodId, quantity);
        return new MealDto(meal);
    }
}
