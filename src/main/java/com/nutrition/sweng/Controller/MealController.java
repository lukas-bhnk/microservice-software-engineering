package com.nutrition.sweng.Controller;

import com.nutrition.sweng.DTO.FoodDto;
import com.nutrition.sweng.DTO.MealDto;
import com.nutrition.sweng.Model.Food;
import com.nutrition.sweng.Model.Meal;
import com.nutrition.sweng.Model.MealCategory;
import com.nutrition.sweng.Service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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

    @GetMapping("/{day}/{month}/{year}/{email}")
    public List<Meal> getMeal(@PathVariable int day, @PathVariable int month, @PathVariable int year, @PathVariable String email) throws ParseException {
        List<Meal> meals = this.mealService.getDailyMeals(new SimpleDateFormat("yyyy-MM-dd").parse(year+"-"+month+"-"+day), email);
        return meals;
    }

    @PatchMapping("/{mealId}/{foodId}/{quantity}")
    public MealDto updateFoodQuantity(@PathVariable Long mealId, @PathVariable Long foodId, @PathVariable Integer quantity){
        Meal meal = this.mealService.updateQuantity(mealId, foodId, quantity);
        return new MealDto(meal);
    }

    @PostMapping("/{mealId}/{foodId}/{quantity}")
    public MealDto addFood(@PathVariable Long mealId, @PathVariable Long foodId, @PathVariable Integer quantity){
        Meal meal = this.mealService.addFood(mealId, foodId, quantity);
        return new MealDto(meal);
    }

    /**
     * Create a Meal for a specif day, Mealcategory and user
     * @param day
     * @param month
     * @param year
     * @param category (breakfast, lunch, dinner, snack)
     * @param email of the user
     * @return created food
     */
    @PostMapping("/{day}/{month}/{year}/{category}/{email}")
    public MealDto createMeal(@PathVariable int day, @PathVariable int month, @PathVariable int year, @PathVariable String category, @PathVariable String email) throws ParseException {
        MealCategory mealCategory;
        System.out.println(category);
        if(category.equals("breakfast")) {mealCategory = MealCategory.BREAKFAST;}
        else {if (category.equals("lunch")){mealCategory = MealCategory.LUNCH;}
        else {if (category.equals("dinner")) {mealCategory = MealCategory.DINNER;}
        else {if (category.equals("snack")) {mealCategory = MealCategory.SNACK;}
        else throw new IllegalArgumentException("Can not resolve Category");}}}
        Meal meal = this.mealService.createMeal(new SimpleDateFormat("yyyy-MM-dd").parse(year+"-"+month+"-"+day), mealCategory, email);
        return new MealDto(meal);
    }

    /**
     * Delete a food by its it from the meal
     * @param mealId
     * @param foodId
     * @return changed meal
     */
    @DeleteMapping("/{mealId}/{foodId}")
    public MealDto deleteFood(@PathVariable Long mealId, @PathVariable Long foodId){
        Meal meal = this.mealService.deleteFood(mealId, foodId);
        return new MealDto(meal);
    }
}
