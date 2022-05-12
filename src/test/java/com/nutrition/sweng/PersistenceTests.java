package com.nutrition.sweng;

import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Repository.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;


@DataJpaTest
public class PersistenceTests {

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodEntryRepository foodEntryRepository;

    @Autowired
    private NutritionalValuesRepository nutritionalValuesRepository;

    @Autowired
    private VitaminsRepository vitaminsRepository;

    @Autowired
    private MineralsRepository mineralsRepository;


    @Test
    public void findMealById() {
        Optional<Meal> mealOptional = mealRepository.findById(1L);
        Meal meal = mealOptional.get();
        assert (meal.getId()==1L);
        Optional<Meal> mealOptional2 = mealRepository.findById(10L);
        assertFalse (mealOptional2.isPresent());
    }

/*
    Test database connection with FoodRepository
    search after the Food Id in the Repository and checks if the Food can find
    search after a not existing Food Id in the Repository and checks if the Food is null
*/
    @Test
    public void findFoodById() {
        Optional<Food> foodOptional = foodRepository.findById(1L);
        Food food = foodOptional.get();
        assert (food.getId()==1L);
        Optional<Food> foodOptional2 = foodRepository.findById(10L);
        assertFalse (foodOptional2.isPresent());
    }

/*
    Test database connection with FoodRepository
    search after the Food Name in the Repository and checks if the Food can find
    search after a not existing Food Name in the Repository and checks if the Food is null
*/
    @Test
    public void findFoodByName(){
        //test query for food Apfel
        List<Food> foodList = foodRepository.findByName("pfe");
        Food foodOptional = foodList.get(0);
        assert (foodOptional.getId()==1L);
        List<Food> foodOptional2 = foodRepository.findByName("Software Engineering is cool :)");
        assertTrue (foodOptional2.isEmpty());
    }

    /*
        Test database connection with NutritionalValuesRepository
        search after the NutritionalValues Id in the Repository and checks if the NutritionalValues can find
        search after a not existing NutritionalValues Id in the Repository and checks if the NutritionalValues is null
    */
    @Test
    public void findNutritionalValuesById() {
        Optional<NutritionalValues> nutritionalValuesOptional = nutritionalValuesRepository.findById(1L);
        NutritionalValues nutritionalValues = nutritionalValuesOptional.get();
        assert (nutritionalValues.getId()==1L);
        Optional<NutritionalValues> nutritionalValuesOptional2 = nutritionalValuesRepository.findById(10L);
        assertFalse (nutritionalValuesOptional2.isPresent());
    }

/*
    Test database connection with VitaminsRepository
    search after the Vitamins Id in the Repository and checks if the Vitamins can find
    search after a not existing Vitamins Id in the Repository and checks if the Vitamins is null
*/
    @Test
    public void findVitaminsById(){
        Optional<Vitamins> vitaminsOptional = vitaminsRepository.findById(1L);
        Vitamins vitamins = vitaminsOptional.get();
        assert (vitamins.getId()==1L);
        Optional<Vitamins> vitaminsOptional2 = vitaminsRepository.findById(10L);
        assertFalse (vitaminsOptional2.isPresent());
    }


    @Test
    public void saveAndDeleteMeal() {
        Meal meal = new Meal(10L,new Date(2000, 10, 21) , 40, MealCategory.BREAKFAST, 2.9, 3.0, 4.0, new AppUser(), Collections.emptySet());
        Meal m = mealRepository.save(meal);
        Optional<Meal> mealOptional = mealRepository.findById(10L);
        assert  (mealOptional.isPresent());
        Meal meal2 = mealOptional.get();
        mealRepository.delete(meal2);
        Optional<Meal> mealOptional2 = mealRepository.findById(10L);
        assertFalse(mealOptional2.isPresent());
    }

    @Test
    public void saveAndFindAllFoodValues() {
        Food food = new Food(1000L,"test", FoodUnitSize.MILLILITRE, Collections.emptySet());
        Minerals minerals = new Minerals(1000L, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, food);
        Vitamins vitamins = new Vitamins(1000L, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, food);
        NutritionalValues nutritionalValues = new NutritionalValues(1000L, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, food);
        food.setMinerals(minerals);
        food.setVitamins(vitamins);
        food.setNutritionalValues(nutritionalValues);
        Food f = foodRepository.save(food);
        Minerals m = mineralsRepository.save(minerals);
        Vitamins v = vitaminsRepository.save(vitamins);
        NutritionalValues nv = nutritionalValuesRepository.save(nutritionalValues);
        Optional<Food> foodOptional = foodRepository.findById(1000L);
        assert  (foodOptional.isPresent());
        Optional<Minerals> mineralsOptional = mineralsRepository.findById(1000L);
        assert  (mineralsOptional.isPresent());
        Optional<Vitamins> vitaminsOptional = vitaminsRepository.findById(1000L);
        assert  (vitaminsOptional.isPresent());
        Optional<NutritionalValues> nutritionalValuesOptional = nutritionalValuesRepository.findById(1000L);
        assert  (nutritionalValuesOptional.isPresent());
    }


}
