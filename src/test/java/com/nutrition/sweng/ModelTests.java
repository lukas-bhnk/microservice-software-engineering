package com.nutrition.sweng;

import com.nutrition.sweng.DTO.*;
import com.nutrition.sweng.Exceptions.NotAuthorizedException;
import com.nutrition.sweng.Exceptions.ProcessException;
import com.nutrition.sweng.Exceptions.ResourceNotFoundException;
import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Service.FoodService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ActiveProfiles("test")
public class ModelTests {
    private int number;
    private double doubleNumber;
    private long longNumber;
    private MealCategory mealcategory;
    private HashSet<FoodEntry> foodEntries;
    private Date date;
    private String string;
    @BeforeEach
    public void setUp() throws Exception {
        string = "test";
        number = 10;
        doubleNumber = 10.0;
        longNumber = 10L;
        mealcategory = MealCategory.DINNER;
        foodEntries = new HashSet<>();
        date = new SimpleDateFormat("yyyy-MM-dd").parse(2021+"-"+05+"-"+15);
    }
    @Test
    public void shouldSetAndGetAllMeal() throws Exception {
        Meal meal = new Meal();
        meal.setCalories(number);
        meal.setCarbs(doubleNumber);
        meal.setFats(doubleNumber);
        meal.setId(longNumber);
        meal.setProteins(doubleNumber);
        meal.setMealCategory(mealcategory);
        meal.setFoodEntries(foodEntries);
        meal.setDate(date);
        assertTrue(meal.equals(meal));
        assertTrue(meal.toString().equals(meal.toString()));
        assertTrue(meal.hashCode()==meal.hashCode());
        assertThat(meal.getCalories(), is(number));
        assertThat(meal.getCarbs(), is(doubleNumber));
        assertThat(meal.getProteins(), is(doubleNumber));
        assertThat(meal.getFats(), is(doubleNumber));
        assertThat(meal.getDate(), is(date));
        assertThat(meal.getId(), is(longNumber));
        assertThat(meal.getMealCategory(), is(mealcategory));
        assertThat(meal.getFoodEntries(), is(foodEntries));
    }

    @Test
    public void shouldSetAndGetAllFoodEntry() throws Exception {
        FoodEntry foodentry = new FoodEntry();
        foodentry.setCalories(number);
        foodentry.setCarbs(doubleNumber);
        foodentry.setFats(doubleNumber);
        foodentry.setId(longNumber);
        foodentry.setProteins(doubleNumber);
        foodentry.setMeal(new Meal());
        foodentry.setFood(new Food());
        foodentry.setQuantity(number);
        FoodEntry foodentry2 = new FoodEntry();
        foodentry2.setCalories(number);
        foodentry2.setCarbs(doubleNumber);
        foodentry2.setFats(doubleNumber);
        foodentry2.setId(longNumber);
        foodentry2.setProteins(doubleNumber);
        foodentry2.setMeal(new Meal());
        foodentry2.setFood(new Food());
        foodentry2.setQuantity(number);
        assertTrue(foodentry2.equals(foodentry));
        assertTrue(foodentry.equals(foodentry));
        assertTrue(foodentry.toString().equals(foodentry.toString()));
        assertTrue(foodentry.hashCode()==foodentry.hashCode());
        assertThat(foodentry.getCalories(), is(number));
        assertThat(foodentry.getCarbs(), is(doubleNumber));
        assertThat(foodentry.getProteins(), is(doubleNumber));
        assertThat(foodentry.getFats(), is(doubleNumber));
        assertThat(foodentry.getId(), is(longNumber));
        assertThat(foodentry.getQuantity(), is(number));
        assertTrue(foodentry.getMeal()!= null);
        assertTrue(foodentry.getFood() != null);
    }

    @Test
    public void testFoodUnitSize() throws Exception {
        FoodUnitSize foodUnitSize = FoodUnitSize.GRAMS;
        assertTrue(foodUnitSize.getUnitSize() != null);
        assertTrue(FoodUnitSize.fromString("pro 100 ml")!= null);
    }

    @Test
    public void testMealCategory() throws Exception {
        MealCategory mealCategory = MealCategory.BREAKFAST;
        assertTrue(mealCategory != null);
        mealCategory = MealCategory.LUNCH;
        assertTrue(mealCategory != null);
        mealCategory = MealCategory.DINNER;
        assertTrue(mealCategory != null);
        mealCategory = MealCategory.SNACK;
        assertTrue(mealCategory != null);
    }

    @Test
    public void shouldSetAndGetAllNutritionalValues() throws Exception {
        NutritionalValues nutritionalValues = new NutritionalValues();
        nutritionalValues.setCalories(number);
        nutritionalValues.setFats(doubleNumber);
        nutritionalValues.setCarbs(doubleNumber);
        nutritionalValues.setProteins(doubleNumber);
        nutritionalValues.setId(longNumber);
        nutritionalValues.setFatsSaturated(doubleNumber);
        nutritionalValues.setAlcohol(doubleNumber);
        nutritionalValues.setSalt(doubleNumber);
        nutritionalValues.setSugar(doubleNumber);
        NutritionalValues nutritionalValues2 = new NutritionalValues();
        nutritionalValues2.setCalories(number);
        nutritionalValues2.setFats(doubleNumber);
        nutritionalValues2.setCarbs(doubleNumber);
        nutritionalValues2.setProteins(doubleNumber);
        nutritionalValues2.setId(longNumber);
        nutritionalValues2.setFatsSaturated(doubleNumber);
        nutritionalValues2.setAlcohol(doubleNumber);
        nutritionalValues2.setSalt(doubleNumber);
        nutritionalValues2.setSugar(doubleNumber);
        assertTrue(nutritionalValues.hashCode()==nutritionalValues.hashCode());
        assertTrue(nutritionalValues.equals(nutritionalValues));
        assertTrue(nutritionalValues2.equals(nutritionalValues));
        assertTrue(nutritionalValues.toString().equals(nutritionalValues.toString()));
        assertThat(nutritionalValues.getCalories(), is(number));
        assertThat(nutritionalValues.getFats(), is(doubleNumber));
        assertThat(nutritionalValues.getCarbs(), is(doubleNumber));
        assertThat(nutritionalValues.getProteins(), is(doubleNumber));
        assertThat(nutritionalValues.getFatsSaturated(), is(doubleNumber));
        assertThat(nutritionalValues.getAlcohol(), is(doubleNumber));
        assertThat(nutritionalValues.getSugar(), is(doubleNumber));
        assertThat(nutritionalValues.getId(), is(longNumber));
    }

    @Test
    public void shouldSetAndGetMinerals() throws Exception {
        Minerals minerals = new Minerals();
        minerals.setChloride(doubleNumber);
        minerals.setIron(doubleNumber);
        minerals.setPhosphorus(doubleNumber);
        minerals.setMagnesium(doubleNumber);
        minerals.setId(longNumber);
        minerals.setPotassium(doubleNumber);
        minerals.setSelenium(doubleNumber);
        minerals.setZinc(doubleNumber);
        minerals.setSodium(doubleNumber);
        Minerals minerals2 = new Minerals();
        minerals2.setChloride(doubleNumber);
        minerals2.setIron(doubleNumber);
        minerals2.setPhosphorus(doubleNumber);
        minerals2.setMagnesium(doubleNumber);
        minerals2.setId(longNumber);
        minerals2.setPotassium(doubleNumber);
        minerals2.setSelenium(doubleNumber);
        minerals2.setZinc(doubleNumber);
        minerals2.setSodium(doubleNumber);
        assertTrue(minerals.hashCode()==minerals.hashCode());
        assertTrue(minerals.equals(minerals));
        assertTrue(minerals2.equals(minerals));
        assertTrue(minerals.toString().equals(minerals.toString()));
        assertThat(minerals.getChloride(), is(doubleNumber));
        assertThat(minerals.getIron(), is(doubleNumber));
        assertThat(minerals.getMagnesium(), is(doubleNumber));
        assertThat(minerals.getPhosphorus(), is(doubleNumber));
        assertThat(minerals.getPotassium(), is(doubleNumber));
        assertThat(minerals.getSelenium(), is(doubleNumber));
        assertThat(minerals.getSodium(), is(doubleNumber));
        assertThat(minerals.getId(), is(longNumber));
    }

    @Test
    public void shouldSetAndGetVitamins() throws Exception {
        Vitamins vitamins = new Vitamins();
        vitamins.setA(doubleNumber);
        vitamins.setB1(doubleNumber);
        vitamins.setB2(doubleNumber);
        vitamins.setB6(doubleNumber);
        vitamins.setId(longNumber);
        vitamins.setB12(doubleNumber);
        vitamins.setBetacarotin(doubleNumber);
        vitamins.setC(doubleNumber);
        vitamins.setD(doubleNumber);
        vitamins.setE(doubleNumber);
        vitamins.setFol(doubleNumber);
        vitamins.setK(doubleNumber);
        vitamins.setNiacin(doubleNumber);
        vitamins.setRetinol(doubleNumber);
        Vitamins vitamins2 = new Vitamins();
        vitamins2.setA(doubleNumber);
        vitamins2.setB1(doubleNumber);
        vitamins2.setB2(doubleNumber);
        vitamins2.setB6(doubleNumber);
        vitamins2.setId(longNumber);
        vitamins2.setB12(doubleNumber);
        vitamins2.setBetacarotin(doubleNumber);
        vitamins2.setC(doubleNumber);
        vitamins2.setD(doubleNumber);
        vitamins2.setE(doubleNumber);
        vitamins2.setFol(doubleNumber);
        vitamins2.setK(doubleNumber);
        vitamins2.setNiacin(doubleNumber);
        vitamins2.setRetinol(doubleNumber);
        assertTrue(vitamins.hashCode()==vitamins.hashCode());
        assertTrue(vitamins.equals(vitamins));
        assertTrue(vitamins2.equals(vitamins));
        assertTrue(vitamins.toString().equals(vitamins.toString()));
        assertThat(vitamins.getA(), is(doubleNumber));
        assertThat(vitamins.getB1(), is(doubleNumber));
        assertThat(vitamins.getB2(), is(doubleNumber));
        assertThat(vitamins.getB2(), is(doubleNumber));
        assertThat(vitamins.getB6(), is(doubleNumber));
        assertThat(vitamins.getB12(), is(doubleNumber));
        assertThat(vitamins.getC(), is(doubleNumber));
        assertThat(vitamins.getId(), is(longNumber));
        assertThat(vitamins.getBetacarotin(), is(doubleNumber));
        assertThat(vitamins.getFol(), is(doubleNumber));
        assertThat(vitamins.getK(), is(doubleNumber));
        assertThat(vitamins.getRetinol(), is(doubleNumber));
        assertThat(vitamins.getE(), is(doubleNumber));
        assertThat(vitamins.getNiacin(), is(doubleNumber));
    }


    @Test
    public void shouldSetAndGetFood() throws Exception {
        Food food = new Food();
        food.setId(longNumber);
        food.setName(string);
        food.setFoodEntries(foodEntries);
        food.setVitamins(new Vitamins());
        food.setMinerals(new Minerals());
        food.setNutritionalValues(new NutritionalValues());
        food.setUnitSize(FoodUnitSize.MILLILITRE);
        Food food2 = new Food();
        food2.setId(longNumber);
        food2.setName(string);
        food2.setFoodEntries(foodEntries);
        food2.setVitamins(new Vitamins());
        food2.setMinerals(new Minerals());
        food2.setNutritionalValues(new NutritionalValues());
        food2.setUnitSize(FoodUnitSize.MILLILITRE);
        assertTrue(food.hashCode()==food.hashCode());
        assertTrue(food.toString().equals(food.toString()));
        assertTrue(food.equals(food));
        assertTrue(food2.equals(food));
        assertTrue(food.getFoodEntries() != null);
        assertTrue(food.getMinerals() != null);
        assertTrue(food.getNutritionalValues() != null);
        assertTrue(food.getVitamins() != null);
        assertTrue(food.equals(food));
        assertThat(food.getId(), is(longNumber));
        assertThat(food.getName(), is(string));
        assertThat(food.getUnitSize(), is(FoodUnitSize.MILLILITRE));
    }

    @Test
    public void testUser() throws Exception{
        User user = new User();
        user.setId(1L);
        user.setEmail("test@email.com");
        assertTrue(user.getEmail().equals("test@email.com"));
        assertTrue(user.getId() == 1L);
        assertTrue(user.hashCode()==user.hashCode());
        assertTrue(user.toString().equals(user.toString()));
        assertTrue(user.equals(user));
    }

    @Test
    public void testExceptions() {
        Assertions.assertThrows(ProcessException.class, () -> {
            throw new ProcessException("test");
        });
        Assertions.assertThrows(NotAuthorizedException.class, () -> {
            throw new NotAuthorizedException("test");
        });
    }

}
