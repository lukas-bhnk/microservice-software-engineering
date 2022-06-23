package com.nutrition.sweng;

import com.nutrition.sweng.Event.MealAddedEvent;
import com.nutrition.sweng.Event.MealChangedEvent;
import com.nutrition.sweng.Event.UserRegisteredEvent;
import com.nutrition.sweng.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
public class EventGetterAndSetterTests {
    private int number;
    private double doubleNumber;
    private long longNumber;
    private String mealcategory;
    private HashSet<FoodEntry> foodEntries;
    private Date date;
    private String string;
    @BeforeEach
    public void setUp() throws Exception {
        string = "test";
        number = 10;
        doubleNumber = 10.0;
        longNumber = 10L;
        mealcategory = MealCategory.DINNER.name();
        foodEntries = new HashSet<>();
        date = new SimpleDateFormat("yyyy-MM-dd").parse(2021+"-"+05+"-"+15);
    }
    @Test
    public void shouldSetAndGetAllMealChangedEvent() throws Exception {
        MealChangedEvent meal = new MealChangedEvent();
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
        assertTrue(meal.hashCode() == meal.hashCode());
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
    public void shouldSetAndGetAllMealAddedEvent() throws Exception {
        MealAddedEvent meal = new MealAddedEvent();
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
        assertTrue(meal.hashCode() == meal.hashCode());
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
    public void shouldSetAndGetAllUserRegisteredEvent() throws Exception {
        UserRegisteredEvent user = new UserRegisteredEvent();
        user.setUserId(longNumber);
        user.setEmail(string);
        assertTrue(user.equals(user));
        assertTrue(user.toString().equals(user.toString()));
        assertTrue(user.hashCode() == user.hashCode());
        assertThat(user.getUserId(), is(longNumber));
        assertThat(user.getEmail(), is(string));
    }
}
