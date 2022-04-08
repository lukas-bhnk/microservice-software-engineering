package com.nutrition.sweng.Model;

import java.util.List;
import java.util.Optional;

public interface VitaminsRepository {

    List<Vitamins> findById(long id);
    Vitamins save(Vitamins v);
    void delete(Vitamins v);

}