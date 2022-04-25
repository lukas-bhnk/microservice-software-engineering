package com.nutrition.sweng.Outbound;

import com.nutrition.sweng.Model.Minerals;
import com.nutrition.sweng.Model.MineralsRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MineralsJpaRepository extends CrudRepository<Minerals, Long>, MineralsRepository {

}
