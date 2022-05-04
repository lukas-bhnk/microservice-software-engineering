package com.nutrition.sweng.Outbound;

import com.nutrition.sweng.Model.FoodEntry;
import com.nutrition.sweng.Model.Meal;
import com.nutrition.sweng.Repository.FoodEntryRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodEntryJpaRepository extends CrudRepository<FoodEntry, Long>, FoodEntryRepository {

}
