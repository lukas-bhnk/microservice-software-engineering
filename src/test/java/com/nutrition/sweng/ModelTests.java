package com.nutrition.sweng;

import com.nutrition.sweng.DTO.*;
import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Service.FoodService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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
    public void shouldSetAndGetAllMealDTO() throws Exception {
        Meal meal = new Meal();
        meal.setCalories(number);
        meal.setCarbs(doubleNumber);
        meal.setFats(doubleNumber);
        meal.setId(longNumber);
        meal.setProteins(doubleNumber);
        meal.setMealCategory(mealcategory);
        meal.setFoodEntries(foodEntries);
        meal.setDate(date);
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
    public void shouldSetAndGetAllNutritionalValuesDTO() throws Exception {
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
        assertThat(nutritionalValues.getCalories(), is(doubleNumber));
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
        food.setUnitSize(FoodUnitSize.MILLILITRE);
        assertThat(food.getId(), is(longNumber));
        assertThat(food.getName(), is(string));
        assertThat(food.getUnitSize(), is(FoodUnitSize.MILLILITRE));

    }

}
