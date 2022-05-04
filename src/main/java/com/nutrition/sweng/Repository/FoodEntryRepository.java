package com.nutrition.sweng.Repository;

import com.nutrition.sweng.Model.FoodEntry;

import java.util.Optional;

public interface FoodEntryRepository {
    Optional<FoodEntry> findById(Long id);
    FoodEntry save(FoodEntry foodEntry);
    void delete(FoodEntry foodEntry);
}
