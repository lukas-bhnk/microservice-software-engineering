package com.nutrition.sweng.Model;

import java.util.List;
import java.util.Optional;

public interface MealRepository {

    Optional<Meal> findById(int id);
    Meal save(Meal m);
    void delete(Meal m);
}