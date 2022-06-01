package com.nutrition.sweng.Repository;

import com.nutrition.sweng.Model.Meal;
import com.nutrition.sweng.Model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MealRepository {
    List<Meal> findByDateAndUser(Date date, User userFk);
    Optional<Meal> findByIdAndUser(Long id, User userFk);
    Meal save(Meal m);
    void delete(Meal m);
}