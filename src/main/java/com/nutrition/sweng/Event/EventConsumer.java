package com.nutrition.sweng.Event;

import com.nutrition.sweng.Model.MealCategory;
import com.nutrition.sweng.Service.MealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.function.Consumer;

@Component
public class EventConsumer implements Consumer<UserRegisteredEvent>{

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private MealService mealService;

    @Autowired
    public EventConsumer(MealService mealService) {
        this.mealService = mealService;
    }

    @Override
    public void accept(UserRegisteredEvent userRegisteredEvent) {
        LOG.info("Consumed Event: " + userRegisteredEvent);
        this.mealService.createMeal(
                new Date(),
                MealCategory.BREAKFAST,
                userRegisteredEvent.getEmail()
        );
        this.mealService.createMeal(
                new Date(),
                MealCategory.LUNCH,
                userRegisteredEvent.getEmail()
        );
        this.mealService.createMeal(
                new Date(),
                MealCategory.DINNER,
                userRegisteredEvent.getEmail()
        );
        this.mealService.createMeal(
                new Date(),
                MealCategory.SNACK,
                userRegisteredEvent.getEmail()
        );
    }


}
