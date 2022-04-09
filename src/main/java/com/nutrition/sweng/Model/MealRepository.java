package com.nutrition.sweng.Model;

import java.util.List;

public interface MealRepository {

    List<Meal> findById(long id);
    Vitamins save(Meal m);
    void delete(Meal m);
}