package com.nutrition.sweng.Controller;

import com.nutrition.sweng.DTO.FoodDto;
import com.nutrition.sweng.DTO.MealDto;
import com.nutrition.sweng.Model.Food;
import com.nutrition.sweng.Model.Meal;
import com.nutrition.sweng.Service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

    @GetMapping("/{day}/{month}/{year}/{userFk}")
    public List<MealDto> getMeal(@PathVariable int day, @PathVariable int month, @PathVariable int year, @PathVariable String email) {
        Date d = new Date(day, month, year);
        List<Meal> meals = this.mealService.getDailyMeals(d, email);
        List<MealDto> mealsDto = null;
        for (Meal m : meals) mealsDto.add(new MealDto(m));
        return mealsDto;
    }

    @PostMapping("/{mealId}/{foodId}/{quantity}")
    public MealDto addFood(@PathVariable Long mealId, @PathVariable Long foodId, @PathVariable Integer quantity){
        Meal meal = this.mealService.addFood(mealId, foodId, quantity);
        return new MealDto(meal);
    }

    @DeleteMapping("/{mealId}/{foodId}")
    public MealDto deleteFood(@PathVariable Long mealId, @PathVariable Long foodId){
        Meal meal = this.mealService.deleteFood(mealId, foodId);
        return new MealDto(meal);
    }
}
