package com.nutrition.sweng.Inbound;

import com.nutrition.sweng.Model.NutritionalValues;

public class NutritionalValuesDto {
    private long id;
    private double carbs;
    private double proteins;
    private double calories;
    private double sugar;
    private double fats;
    private double fatsSaturated;
    private double alcohol;
    private double salt;

    public NutritionalValuesDto(){
    }

    public NutritionalValuesDto(NutritionalValues nv){
        this.id = nv.getId();
        this.carbs = nv.getCarbs();
        this.proteins = nv.getProteins();
        this.calories = nv.getCalories();
        this.sugar = nv.getSugar();
        this.fats = nv.getFats();
        this.fatsSaturated = nv.getFatsSaturated();
        this.alcohol = nv.getAlcohol();
        this.salt = nv.getSalt();
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
}
