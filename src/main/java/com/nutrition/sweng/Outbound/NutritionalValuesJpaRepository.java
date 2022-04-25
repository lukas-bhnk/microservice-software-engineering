package com.nutrition.sweng.Outbound;

import com.nutrition.sweng.Model.NutritionalValues;
import com.nutrition.sweng.Repository.NutritionalValuesRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionalValuesJpaRepository extends CrudRepository<NutritionalValues, Long>, NutritionalValuesRepository {

}
