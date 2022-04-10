package com.nutrition.sweng.Model;

import java.util.Optional;

public interface VitaminsRepository {

    Optional<Vitamins> findById(long id);
    Vitamins save(Vitamins v);
    void delete(Vitamins v);

}