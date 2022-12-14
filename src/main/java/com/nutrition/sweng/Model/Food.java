package com.nutrition.sweng.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private FoodUnitSize unitSize;
    @Version
    private long version;

    @JsonIgnore
    @OneToMany(mappedBy = "meal")
    private Set<FoodEntry> foodEntries;


    @JsonIgnore
    @OneToOne(cascade=CascadeType.ALL,
            optional=false,
            mappedBy="food")
    private NutritionalValues nutritionalValues;

    @JsonIgnore
    @OneToOne(cascade=CascadeType.ALL,
            optional=false,
            mappedBy="food")
    private Minerals minerals;

    @JsonIgnore
    @OneToOne(cascade=CascadeType.ALL,
            optional=false,
            mappedBy="food")
    private Vitamins vitamins;

    public Food(){}

    public Food(long id, String name, FoodUnitSize unitSize, Set<FoodEntry> foodEntries) {
        this.id = id;
        this.name = name;
        this.unitSize = unitSize;
        this.foodEntries = foodEntries;
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

    public Set<FoodEntry> getFoodEntries() {
        return foodEntries;
    }

    public void setFoodEntries(Set<FoodEntry> foodEntries) {
        this.foodEntries = foodEntries;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return id == food.id && version == food.version && Objects.equals(name, food.name) && unitSize == food.unitSize && Objects.equals(foodEntries, food.foodEntries) && Objects.equals(nutritionalValues, food.nutritionalValues) && Objects.equals(minerals, food.minerals) && Objects.equals(vitamins, food.vitamins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, unitSize, version);
    }
}
