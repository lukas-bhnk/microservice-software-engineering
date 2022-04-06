package com.nutrition.sweng.Model;

public enum FoodUnitSize {
    GRAMS("pro 100g essbarer Anteil"),
    MILLILITRE("pro 100 ml");

    private String unitSize;

    private FoodUnitSize(String size){
        this.unitSize = size;
    }

    public String getUnitSize() {
        return unitSize;
    }
}
