package com.nutrition.sweng.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Minerals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double chloride;
    private double magnesium;
    private double phosphorus;
    private double iron;
    private double potassium;
    private double selenium;
    private double sodium;
    private double zink;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getChloride() {
        return chloride;
    }

    public void setChloride(double chloride) {
        this.chloride = chloride;
    }

    public double getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(double magnesium) {
        this.magnesium = magnesium;
    }

    public double getPhosphorus() {
        return phosphorus;
    }

    public void setPhosphorus(double phosphorus) {
        this.phosphorus = phosphorus;
    }

    public double getIron() {
        return iron;
    }

    public void setIron(double iron) {
        this.iron = iron;
    }

    public double getPotassium() {
        return potassium;
    }

    public void setPotassium(double potassium) {
        this.potassium = potassium;
    }

    public double getSelenium() {
        return selenium;
    }

    public void setSelenium(double selenium) {
        this.selenium = selenium;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getZink() {
        return zink;
    }

    public void setZink(double zink) {
        this.zink = zink;
    }
}