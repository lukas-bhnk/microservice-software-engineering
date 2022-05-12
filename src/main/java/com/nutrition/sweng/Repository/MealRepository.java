package com.nutrition.sweng.Repository;

import com.nutrition.sweng.Model.Meal;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MealRepository {
    List<Meal> findByDateAndUser(Date date, Long userFk);
    Optional<Meal> findById(Long id);
    Meal save(Meal m);
    void delete(Meal m);
}