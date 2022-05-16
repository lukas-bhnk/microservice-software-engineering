package com.nutrition.sweng.DTO;

import com.nutrition.sweng.Model.Food;
import com.nutrition.sweng.Model.FoodUnitSize;

public class FoodDto {
    private long id;
    private String name;
    private String unitSize;

    public FoodDto(){
    }

    public FoodDto(Food f){
        this(f.getId(), f.getName(), f.getUnitSize());
    }

    public FoodDto(long id, String name, FoodUnitSize unitSize) {
        this.id = id;
        this.name = name;
        this.unitSize = unitSize.getUnitSize();
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


    public String getUnitSize() {
        return unitSize;
    }


    public void setUnitSize(String unitSize) {
        this.unitSize = unitSize;
    }

}

