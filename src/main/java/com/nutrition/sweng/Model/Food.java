package com.nutrition.sweng.Model;

import javax.persistence.*;

@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private FoodUnitSize unitSize;

    @OneToOne(cascade=CascadeType.ALL,
            optional=false,
            mappedBy="food")
    private NutritionalValues nutritionalValues;

    @OneToOne(cascade=CascadeType.ALL,
            optional=false,
            mappedBy="food")
    private Minerals minerals;

    @OneToOne(cascade=CascadeType.ALL,
            optional=false,
            mappedBy="food")
    private Vitamins vitamins;

    public Food(){}

    public Food(String name, FoodUnitSize unitSize) {
        this.name = name;
        this.unitSize = unitSize;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FoodUnitSize getUnitSize() {
        return unitSize;
    }

    public void setUnitSize(FoodUnitSize unitSize) {
        this.unitSize = unitSize;
    }

    public NutritionalValues getNutritionalValues() {
        return nutritionalValues;
    }

    public void setNutritionalValues(NutritionalValues nutritionalValues) {
        this.nutritionalValues = nutritionalValues;
    }

    public Minerals getMinerals() {
        return minerals;
    }

    public void setMinerals(Minerals minerals) {
        this.minerals = minerals;
    }

    public Vitamins getVitamins() {
        return vitamins;
    }

    public void setVitamins(Vitamins vitamins) {
        this.vitamins = vitamins;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitSize='" + unitSize.name() + '\'' +
                '}';
    }

}
