package com.nutrition.sweng.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date;
    private Integer calories;
    private MealCategory mealCategory;
    private Double fats;
    private Double carbs;
    private Double proteins;
    private long userFk;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "MEAL_FK")
    private Set<Food> foodList;

    public Meal() {

    }

    public Meal(Long id, Date date, Integer calories, MealCategory mealCategory, Double fats, Double carbs, Double proteins, Long userFk, Set<Food> foodList) {
        this.id = id;
        this.date = date;
        this.calories = calories;
        this.mealCategory = mealCategory;
        this.fats = fats;
        this.carbs = carbs;
        this.proteins = proteins;
        this.userFk = userFk;
        this.foodList = foodList;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserFk() {
        return userFk;
    }

    public void setUserFk(long userFk) {
        this.userFk = userFk;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public MealCategory getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(MealCategory mealCategory) {
        this.mealCategory = mealCategory;
    }

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public Double getProteins() {
        return proteins;
    }

    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }


    public Set<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(Set<Food> foodList) {
        this.foodList = foodList;
    }

}
