package com.nutrition.sweng.Model;

import javax.persistence.*;
import java.util.Set;

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
}
