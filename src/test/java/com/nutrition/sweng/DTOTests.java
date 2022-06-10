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
public class DTOTests {
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
    public void shouldSetAndGetAllMealDTO() throws Exception {
        MealDto mealDto = new MealDto();
        mealDto.setCalories(number);
        mealDto.setCarbs(doubleNumber);
        mealDto.setFats(doubleNumber);
        mealDto.setId(longNumber);
        mealDto.setProteins(doubleNumber);
        mealDto.setMealCategory(mealcategory);
        mealDto.setFoodEntries(foodEntries);
        mealDto.setDate(date);
        assertThat(mealDto.getCalories(), is(number));
        assertThat(mealDto.getCarbs(), is(doubleNumber));
        assertThat(mealDto.getProteins(), is(doubleNumber));
        assertThat(mealDto.getFats(), is(doubleNumber));
        assertThat(mealDto.getDate(), is(date));
        assertThat(mealDto.getId(), is(longNumber));
        assertThat(mealDto.getMealCategory(), is(mealcategory));
        assertThat(mealDto.getFoodEntries(), is(foodEntries));
    }

    @Test
    public void shouldSetAndGetAllNutritionalValuesDTO() throws Exception {
        NutritionalValuesDto nutritionalValuesDto = new NutritionalValuesDto();
        nutritionalValuesDto.setCalories(number);
        nutritionalValuesDto.setFats(doubleNumber);
        nutritionalValuesDto.setCarbs(doubleNumber);
        nutritionalValuesDto.setProteins(doubleNumber);
        nutritionalValuesDto.setId(longNumber);
        nutritionalValuesDto.setFatsSaturated(doubleNumber);
        nutritionalValuesDto.setAlcohol(doubleNumber);
        nutritionalValuesDto.setSalt(doubleNumber);
        nutritionalValuesDto.setSugar(doubleNumber);
        assertThat(nutritionalValuesDto.getCalories(), is(doubleNumber));
        assertThat(nutritionalValuesDto.getFats(), is(doubleNumber));
        assertThat(nutritionalValuesDto.getCarbs(), is(doubleNumber));
        assertThat(nutritionalValuesDto.getProteins(), is(doubleNumber));
        assertThat(nutritionalValuesDto.getFatsSaturated(), is(doubleNumber));
        assertThat(nutritionalValuesDto.getAlcohol(), is(doubleNumber));
        assertThat(nutritionalValuesDto.getSugar(), is(doubleNumber));
        assertThat(nutritionalValuesDto.getId(), is(longNumber));
    }

    @Test
    public void shouldSetAndGetMinerals() throws Exception {
        MineralsDto mineralsDto = new MineralsDto();
        mineralsDto.setChloride(doubleNumber);
        mineralsDto.setIron(doubleNumber);
        mineralsDto.setPhosphorus(doubleNumber);
        mineralsDto.setMagnesium(doubleNumber);
        mineralsDto.setId(longNumber);
        mineralsDto.setPotassium(doubleNumber);
        mineralsDto.setSelenium(doubleNumber);
        mineralsDto.setZinc(doubleNumber);
        mineralsDto.setSodium(doubleNumber);
        assertThat(mineralsDto.getChloride(), is(doubleNumber));
        assertThat(mineralsDto.getIron(), is(doubleNumber));
        assertThat(mineralsDto.getMagnesium(), is(doubleNumber));
        assertThat(mineralsDto.getPhosphorus(), is(doubleNumber));
        assertThat(mineralsDto.getPotassium(), is(doubleNumber));
        assertThat(mineralsDto.getSelenium(), is(doubleNumber));
        assertThat(mineralsDto.getSodium(), is(doubleNumber));
        assertThat(mineralsDto.getId(), is(longNumber));
    }

    @Test
    public void shouldSetAndGetVitamins() throws Exception {
        VitaminsDto vitaminsDto = new VitaminsDto();
        vitaminsDto.setA(doubleNumber);
        vitaminsDto.setB1(doubleNumber);
        vitaminsDto.setB2(doubleNumber);
        vitaminsDto.setB6(doubleNumber);
        vitaminsDto.setId(longNumber);
        vitaminsDto.setB12(doubleNumber);
        vitaminsDto.setBetacarotin(doubleNumber);
        vitaminsDto.setC(doubleNumber);
        vitaminsDto.setD(doubleNumber);
        vitaminsDto.setE(doubleNumber);
        vitaminsDto.setFol(doubleNumber);
        vitaminsDto.setK(doubleNumber);
        vitaminsDto.setNiacin(doubleNumber);
        vitaminsDto.setRetinol(doubleNumber);
        assertThat(vitaminsDto.getA(), is(doubleNumber));
        assertThat(vitaminsDto.getB1(), is(doubleNumber));
        assertThat(vitaminsDto.getB2(), is(doubleNumber));
        assertThat(vitaminsDto.getB2(), is(doubleNumber));
        assertThat(vitaminsDto.getB6(), is(doubleNumber));
        assertThat(vitaminsDto.getB12(), is(doubleNumber));
        assertThat(vitaminsDto.getC(), is(doubleNumber));
        assertThat(vitaminsDto.getId(), is(longNumber));
        assertThat(vitaminsDto.getBetacarotin(), is(doubleNumber));
        assertThat(vitaminsDto.getFol(), is(doubleNumber));
        assertThat(vitaminsDto.getK(), is(doubleNumber));
        assertThat(vitaminsDto.getRetinol(), is(doubleNumber));
        assertThat(vitaminsDto.getE(), is(doubleNumber));
        assertThat(vitaminsDto.getNiacin(), is(doubleNumber));
    }


    @Test
    public void shouldSetAndGetFood() throws Exception {
        FoodDto foodDto = new FoodDto();
        foodDto.setId(longNumber);
        foodDto.setName(string);
        foodDto.setUnitSize(FoodUnitSize.MILLILITRE.name());
        assertThat(foodDto.getId(), is(longNumber));
        assertThat(foodDto.getName(), is(string));
        assertThat(foodDto.getUnitSize(), is(FoodUnitSize.MILLILITRE.name()));

    }

}
