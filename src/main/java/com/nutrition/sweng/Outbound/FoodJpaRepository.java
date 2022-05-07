package com.nutrition.sweng.Outbound;
import com.nutrition.sweng.Model.Food;
import com.nutrition.sweng.Repository.FoodRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodJpaRepository extends CrudRepository<Food, Long>, FoodRepository {
    @Query("SELECT f FROM Food f WHERE f.name LIKE %:name%")
    List<Food> findByName(String name);

}
