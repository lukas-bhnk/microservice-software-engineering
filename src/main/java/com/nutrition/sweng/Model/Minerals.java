package com.nutrition.sweng.Model;

import javax.persistence.*;
import java.util.Objects;

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
    private double zinc;

    @Version
    private long version;

    @JoinColumn(name="FOOD_ID")
    @OneToOne
    @MapsId
    private Food food;

    public Minerals(){}

    public Minerals(long id, double chloride, double magnesium, double phosphorus, double iron, double potassium, double selenium, double sodium, double zinc, Food food) {
        this.id = id;
        this.chloride = chloride;
        this.magnesium = magnesium;
        this.phosphorus = phosphorus;
        this.iron = iron;
        this.potassium = potassium;
        this.selenium = selenium;
        this.sodium = sodium;
        this.zinc = zinc;
        this.food = food;
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

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public String toString(){
        return
        "Minerals{" +
                "id=" + id +
                ", chloride='" + chloride + '\'' +
                ", magnesium='" + magnesium + '\'' +
                ", phosphorus='" + phosphorus + '\'' +
                ", iron='" + iron + '\'' +
                ", potassium='" + potassium + '\'' +
                ", selenium='" + selenium + '\'' +
                ", sodium='" + sodium + '\'' +
                ", zinc='" + zinc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Minerals minerals = (Minerals) o;
        return id == minerals.id && Double.compare(minerals.chloride, chloride) == 0 && Double.compare(minerals.magnesium, magnesium) == 0 && Double.compare(minerals.phosphorus, phosphorus) == 0 && Double.compare(minerals.iron, iron) == 0 && Double.compare(minerals.potassium, potassium) == 0 && Double.compare(minerals.selenium, selenium) == 0 && Double.compare(minerals.sodium, sodium) == 0 && Double.compare(minerals.zinc, zinc) == 0 && version == minerals.version && Objects.equals(food, minerals.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chloride, magnesium, phosphorus, iron, potassium, selenium, sodium, zinc, version, food);
    }
}
