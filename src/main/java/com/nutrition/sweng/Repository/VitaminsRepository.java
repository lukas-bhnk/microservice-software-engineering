package com.nutrition.sweng.Repository;

import com.nutrition.sweng.Model.Vitamins;

import java.util.Optional;

public interface VitaminsRepository {

    Optional<Vitamins> findById(long id);
    Vitamins save(Vitamins v);
    void delete(Vitamins v);

}