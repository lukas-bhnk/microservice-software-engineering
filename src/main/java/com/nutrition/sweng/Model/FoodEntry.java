package com.nutrition.sweng.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.Objects;
import java.util.Set;

@Entity
public class FoodEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Version
    private long version;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodEntry foodEntry = (FoodEntry) o;
        return id == foodEntry.id && quantity == foodEntry.quantity && calories == foodEntry.calories && Double.compare(foodEntry.fats, fats) == 0 && Double.compare(foodEntry.carbs, carbs) == 0 && Double.compare(foodEntry.proteins, proteins) == 0 && version == foodEntry.version && Objects.equals(meal, foodEntry.meal) && Objects.equals(food, foodEntry.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, meal, food, quantity, calories, fats, carbs, proteins, version);
    }
}
