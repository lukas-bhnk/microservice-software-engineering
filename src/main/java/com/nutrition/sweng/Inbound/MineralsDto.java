package com.nutrition.sweng.Inbound;

import com.nutrition.sweng.Model.Minerals;

public class MineralsDto {
    private long id;
    private double chloride;
    private double magnesium;
    private double phosphorus;
    private double iron;
    private double potassium;
    private double selenium;
    private double sodium;
    private double zinc;

    public MineralsDto(){
    }

    public MineralsDto(Minerals m){
        this.id = m.getId();
        this.chloride = m.getChloride();
        this.magnesium = m.getMagnesium();
        this.phosphorus = m.getPhosphorus();
        this.iron = m.getIron();
        this.potassium = m.getPotassium();
        this.selenium = m.getSelenium();
        this.sodium = m.getSodium();
        this.zinc = m.getZinc();
    }

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

    public double getZinc() {
        return zinc;
    }

    public void setZinc(double zinc) {
        this.zinc = zinc;
    }


}
