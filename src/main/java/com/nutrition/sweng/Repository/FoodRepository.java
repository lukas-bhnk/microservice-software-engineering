package com.nutrition.sweng.Repository;

import com.nutrition.sweng.Model.Food;

import java.util.List;
import java.util.Optional;

public interface FoodRepository {

    Optional<Food> findById(Long id);
    List<Food> findByName(String name);
    Food save(Food f);

}
