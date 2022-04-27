package com.nutrition.sweng.Repository;

import com.nutrition.sweng.Model.Meal;

import java.util.List;
import java.util.Optional;

public interface MealRepository {
    Optional<Meal> findOneByUserFk(Long userFk);
    Optional<Meal> findById(Long id);
    Meal save(Meal m);
    void delete(Meal m);
}