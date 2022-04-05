package com.nutrition.sweng.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Lunch implements Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date;
    private Integer calories;
    private Double fats;
    private Double carbs;
    private Double proteins;

    public Lunch() {

    }

    public Lunch(Date d, Integer cal, double f, double c, double p){
        this.date = d;
        this.calories = cal;
        this.fats = f;
        this.carbs = c;
        this.proteins = p;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public Date getDate() {
        return this.date;
    }

    @Override
    public void setDate(Date date) {
        this.date=date;
    }

    @Override
    public Integer getCalories() {
        return this.calories;
    }

    @Override
    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    @Override
    public double getFats() {
        return this.fats;
    }

    @Override
    public double setFats(double fats) {
        this.fats = fats;
    }

    @Override
    public double getCarbs() {
        return this.carbs;
    }

    @Override
    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    @Override
    public double getProteins() {
        return this.proteins;
    }

    @Override
    public void setProteins(double proteins) {
        this.proteins = proteins;
    }
}
