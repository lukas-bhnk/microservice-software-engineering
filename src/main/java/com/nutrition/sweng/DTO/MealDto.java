package com.nutrition.sweng.DTO;


import com.nutrition.sweng.Model.Food;
import com.nutrition.sweng.Model.FoodEntry;
import com.nutrition.sweng.Model.Meal;

import java.util.Date;
import java.util.Set;

public class MealDto {
    private long id;
    private Date date;
    private double proteins;
    private double carbs;
    private double fats;
    private int calories;
    private String mealCategory;
    private Set<FoodEntry> foodEntries;


    public MealDto(){
    }

    public MealDto(Meal m){
        this.id = m.getId();
        this.date = m.getDate();
        this.mealCategory = m.getMealCategory().name();
        this.proteins = m.getProteins();
        this.carbs = m.getCarbs();
        this.fats = m.getFats();
        this.calories = m.getCalories();
        this.foodEntries = m.getFoodEntries();
    }

    public Set<FoodEntry> getFoodEntries() {
        return foodEntries;
    }

    public void setFoodList(Set<FoodEntry> foodEntries) {
        this.foodEntries = foodEntries;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }
}

