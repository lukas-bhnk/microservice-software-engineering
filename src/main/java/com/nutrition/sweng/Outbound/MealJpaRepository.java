package com.nutrition.sweng.Outbound;

import com.nutrition.sweng.Model.Meal;
import com.nutrition.sweng.Repository.MealRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealJpaRepository extends CrudRepository<Meal, Integer>, MealRepository {

}