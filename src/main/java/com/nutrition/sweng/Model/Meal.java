package com.nutrition.sweng.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.DATE)
    private Date date;
    private Integer calories;
    private MealCategory mealCategory;
    private Double fats;
    private Double carbs;
    private Double proteins;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    private User userFk;

    @JsonIgnore
    @OneToMany(mappedBy = "meal")
    private Set<FoodEntry> foodEntries;

    public Meal() {

    }

    public Meal(Long id, Date date, Integer calories, MealCategory mealCategory, Double fats, Double carbs, Double proteins, User userFk, Set<FoodEntry> foodEntries) {
        this.id = id;
        this.date = date;
        this.calories = calories;
        this.mealCategory = mealCategory;
        this.fats = fats;
        this.carbs = carbs;
        this.proteins = proteins;
        this.userFk = userFk;
        this.foodEntries = foodEntries;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUserFk() {
        return userFk;
    }

    public void setUserFk(User userFk) {
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
                ", mealCategory='" + mealCategory.name() + '\'' +
                ", fats='" + fats + '\'' +
                ", carbs='" + carbs + '\'' +
                ", proteins='" + proteins + '\'' +
                ", user='" + userFk + '\'' +
                ", foodEntries='" + foodEntries + '\'' +
        '}';
    }

}
