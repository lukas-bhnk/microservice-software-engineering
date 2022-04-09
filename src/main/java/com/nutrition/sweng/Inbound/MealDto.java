package com.nutrition.sweng.Inbound;


import com.nutrition.sweng.Model.Meal;
import com.nutrition.sweng.Model.MealCategory;

import java.util.Date;

public class MealDto {
    private int id;
    private Date date;
    private double proteins;
    private double carbs;
    private double fats;
    private int calories;
    private String mealCategory;


    public MealDto(){
    }

    public MealDto(Meal m){
        this(m.getId(), m.getDate(),m.getMealCategory(),m.getProteins(), m.getCarbs(), m.getFats(), m.getCalories());
    }

    public MealDto(Integer id, Date date, MealCategory mealCategory, double proteins, double carbs, double fats, int calories) {
        this.id = id;
        this.date = date;
        this.mealCategory = mealCategory.name();
        this.proteins = proteins;
        this.carbs = carbs;
        this.fats = fats;
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

