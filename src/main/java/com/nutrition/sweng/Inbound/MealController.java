package com.nutrition.sweng.Inbound;

import com.nutrition.sweng.Model.Meal;
import com.nutrition.sweng.Service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
