package com.nutrition.sweng.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FoodUnitSize {
    @JsonProperty("pro 100g essbarer Anteil")
    GRAMS("pro 100g essbarer Anteil"),
    @JsonProperty("pro 100ml")
    MILLILITRE("pro 100ml");

    private String unitSize;

    private FoodUnitSize(String size){
        this.unitSize = size;
    }

    public String getUnitSize() {
        return unitSize;
    }
}
