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
    /**
     * Returns an Id of the FoodDto instance as a long datatype
     *
     * @return    Id of the FoodDto
     *
     * */
    public long getId() {
        return id;
    }
    /**
     * Sets the Id for the FoodDto instance as a long datatype
     *
     * @param   id   a long number
     * @return none
     *
     * */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * Returns the name of the FoodDto instance as a String datatype
     *
     * @return      name of the FoodDto
     *
     * */

    public String getName() {
        return name;
    }

    /**
     * Sets the name for the FoodDto instance as a String datatype
     *
     * @param   name   a String that names the FoodDto
     * @return none
     *
     * */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the unitSize of the FoodDto instance as a String datatype
     *
     * @return      unitSize of the FoodDto
     *
     * */
    public String getUnitSize() {
        return unitSize;
    }

    /**
     * Sets the name for the FoodDto instance as a String datatype
     *
     * @param   unitSize a String that defines the UnitSize of the FoodDto
     * @return none
     *
     * */
    public void setUnitSize(String unitSize) {
        this.unitSize = unitSize;
    }

}

