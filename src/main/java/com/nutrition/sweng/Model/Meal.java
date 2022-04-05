package com.nutrition.sweng.Model;

import java.util.Date;

public interface Meal {
    public Integer getId();
    public void setId(Integer id);
    public Date getDate();
    public void setDate(Date date);
    public Integer getCalories();
    public void setCalories(Integer calories);
    public double getFats();
    public double setFats(double fats);
    public double getCarbs();
    public void setCarbs(double carbs);
    public double getProteins();
    public void setProteins(double proteins);
}
