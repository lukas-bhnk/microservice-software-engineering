package com.nutrition.sweng.Outbound;
import com.nutrition.sweng.Model.Food;
import com.nutrition.sweng.Model.FoodRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodJpaRepository extends CrudRepository<Food, Long>, FoodRepository {

    List<Food> findByName(String name);

}
