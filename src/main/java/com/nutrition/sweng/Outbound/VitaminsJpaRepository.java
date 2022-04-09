package com.nutrition.sweng.Outbound;

import com.nutrition.sweng.Model.Vitamins;
import com.nutrition.sweng.Model.VitaminsRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VitaminsJpaRepository extends CrudRepository<Vitamins, Long>, VitaminsRepository {

}