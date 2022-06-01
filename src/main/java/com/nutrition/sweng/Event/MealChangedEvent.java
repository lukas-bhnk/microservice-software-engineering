package com.nutrition.sweng.Event;

import com.nutrition.sweng.Model.FoodEntry;
import com.nutrition.sweng.Model.Meal;
import com.nutrition.sweng.Model.MealCategory;
import com.nutrition.sweng.Model.User;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class MealChangedEvent {

    private long id;
    private Date date;
    private String mealCategory;
    private int calories;
    private double proteins;
    private double fats;
    private double carbs;
    private User userFk;

    private Set<FoodEntry> foodEntries;


    public MealChangedEvent(){
    }

    public MealChangedEvent(Long id, Date date, MealCategory mealCategory, int calories, double fats, double carbs, double proteins, User userFk, Set<FoodEntry> foodEntries) {
        this.id = id;
        this.mealCategory = mealCategory.name();
        this.calories = calories;
        this.date = date;
        this.fats = fats;
        this.carbs = carbs;
        this.proteins = proteins;
        this.userFk = userFk;
        this.foodEntries = foodEntries;
    }

    public MealChangedEvent(Meal m) {
        this.id = m.getId();
        this.date = m.getDate();
        this.mealCategory = m.getMealCategory().name();
        this.proteins = m.getProteins();
        this.carbs = m.getCarbs();
        this.fats = m.getFats();
        this.calories = m.getCalories();
        this.userFk = m.getUserFk();
        this.foodEntries = m.getFoodEntries();
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

    public String getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public User getUserFk() {
        return userFk;
    }

    public void setUserFk(User userFk) {
        this.userFk = userFk;
    }

    public Set<FoodEntry> getFoodEntries() {
        return foodEntries;
    }

    public void setFoodEntries(Set<FoodEntry> foodEntries) {
        this.foodEntries = foodEntries;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", date=" + date + '\'' +
                ", calories='" + calories + '\'' +
                ", mealCategory='" + mealCategory + '\'' +
                ", fats='" + fats + '\'' +
                ", carbs='" + carbs + '\'' +
                ", proteins='" + proteins + '\'' +
                ", user='" + userFk + '\'' +
                ", foodEntries='" + foodEntries + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealChangedEvent that = (MealChangedEvent) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(mealCategory, that.mealCategory) && Objects.equals(calories, that.calories)  && Objects.equals(foodEntries, that.foodEntries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, mealCategory, calories, proteins, carbs, fats, foodEntries);
    }

}
