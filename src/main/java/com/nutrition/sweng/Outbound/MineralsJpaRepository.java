package com.nutrition.sweng.Outbound;

import com.nutrition.sweng.Model.FoodRepository;
import com.nutrition.sweng.Model.Minerals;
import com.nutrition.sweng.Model.MineralsRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MineralsJpaRepository extends CrudRepository<Minerals, Integer>, MineralsRepository {

    List<Minerals> findByName(String name);

}
