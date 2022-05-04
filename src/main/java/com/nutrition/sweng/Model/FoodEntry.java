package com.nutrition.sweng.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
public class FoodEntry {
    @Id
    @GeneratedValue
    private long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MEAL_ID")
    private Meal meal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FOOD_ID")
    private Food food;
    private int quantity;
    private int calories;
    private double fats;
    private double carbs;
    private double proteins;

    public FoodEntry(){
    }

    public FoodEntry(long id, Meal meal, Food food, int quantity, int calories, double fats, double carbs, double proteins){
        this.id = id;
        this.meal = meal;
        this.food = food;
        this.quantity = quantity;
        this.calories = calories;
        this.fats = fats;
        this.carbs = carbs;
        this.proteins = proteins;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
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

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }
}
