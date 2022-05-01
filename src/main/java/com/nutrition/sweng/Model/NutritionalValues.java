package com.nutrition.sweng.Model;
import javax.persistence.*;

@Entity
public class NutritionalValues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double carbs;
    private double proteins;
    private double calories;
    private double sugar;
    private double fats;
    private double fatsSaturated;
    private double alcohol;
    private double salt;


    @JoinColumn(name="FOOD_ID")
    @OneToOne
    @MapsId
    private Food food;

    public NutritionalValues(){}

    public NutritionalValues(long id, double carbs, double proteins, double calories, double sugar, double fats, double fatsSaturated, double alcohol, double salt, Food food) {
        this.id = id;
        this.carbs = carbs;
        this.proteins = proteins;
        this.calories = calories;
        this.sugar = sugar;
        this.fats = fats;
        this.fatsSaturated = fatsSaturated;
        this.alcohol = alcohol;
        this.salt = salt;
        this.food = food;
    }

    public NutritionalValues(double cb, double pts, double fts, double cl, double ftsS, double alc){
        this.carbs = cb;
        this.proteins = pts;
        this.calories = cl;
        this.fats = fts;
        this.fatsSaturated = ftsS;
        this.alcohol = alc;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }


    public double getFatsSaturated() {
        return fatsSaturated;
    }

    public void setFatsSaturated(double fatsSaturated) {
        this.fatsSaturated = fatsSaturated;
    }

    public double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    public double getSalt() {
        return salt;
    }

    public void setSalt(double salt) {
        this.salt = salt;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "NutritionalValues{" +
                "id=" + id +
                ", carbs='" + carbs + '\'' +
                ", proteins='" + proteins + '\'' +
                ", fats='" + fats + '\'' +
                ", fatsSaturated='" + fatsSaturated + '\'' +
                ", alcohol='" + alcohol + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
