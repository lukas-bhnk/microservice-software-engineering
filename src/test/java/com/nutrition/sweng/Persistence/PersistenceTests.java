package com.nutrition.sweng.Persistence;

import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Repository.FoodRepository;
import com.nutrition.sweng.Repository.MealRepository;
import static org.junit.jupiter.api.Assertions.*;

import com.nutrition.sweng.Repository.NutritionalValuesRepository;
import com.nutrition.sweng.Repository.VitaminsRepository;
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
    private NutritionalValuesRepository nutritionalValuesRepository;

    @Autowired
    private VitaminsRepository vitaminsRepository;

    /* Test database connection with MealRepository
    search after the Meal Id in the Repository and checks if the Meal can find
    search after a not existing Meal Id in the Repository and checks if the Meal is null
    */
    @Test
    public void findMealById() {
        Optional<Meal> mealOptional = mealRepository.findById(1L);
        Meal meal = mealOptional.get();
        assert (meal.getId()==1L);
        Optional<Meal> mealOptional2 = mealRepository.findById(10L);
        assertFalse (mealOptional2.isPresent());
    }

    /* Test database connection with FoodRepository
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

    /* Test database connection with FoodRepository
     search after the Food Name in the Repository and checks if the Food can find
     search after a not existing Food Name in the Repository and checks if the Food is null
     */
    @Test
    public void findFoodByName(){
        List<Food> foodList = foodRepository.findByName("Apfel");
        Food foodOptional = foodList.get(0);
        assert (foodOptional.getId()==1L);
        List<Food> foodOptional2 = foodRepository.findByName("Software Engineering is cool :)");
        assertTrue (foodOptional2.isEmpty());
    }

    /* Test database connection with NutritionalValuesRepository
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

    /* Test database connection with VitaminsRepository
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

/*    @Test
    public void saveAndDeleteMeal() {
        Meal meal = new Meal(10L,new Date(2000, 10, 21) , 40, MealCategory.BREAKFAST, 2.9, 3.0, 4.0, 10L, Collections.emptySet());
        Meal m = mealRepository.save(meal);
        Optional<Meal> mealOptional = mealRepository.findById(10L);
        assert  (mealOptional.isPresent());
        mealRepository.delete();
    }*/

}
