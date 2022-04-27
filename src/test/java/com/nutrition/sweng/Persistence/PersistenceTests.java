package com.nutrition.sweng.Persistence;

import com.nutrition.sweng.Model.Meal;
import com.nutrition.sweng.Repository.FoodRepository;
import com.nutrition.sweng.Repository.MealRepository;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Iterator;
import java.util.Optional;


@DataJpaTest
public class PersistenceTests {

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private FoodRepository foodRepository;

    /* Test database connection with MealRepository
    findAll() should find 4 Meals
    */
    @Test
    public void findMealById() {
        Optional<Meal> mealOptional = mealRepository.findById(1L);
        Meal meal = mealOptional.get();
        assert (meal.getId()==1.0);
        Optional<Meal> mealOptional2 = mealRepository.findById(10L);
        assertFalse (mealOptional2.isPresent());
    }


}
