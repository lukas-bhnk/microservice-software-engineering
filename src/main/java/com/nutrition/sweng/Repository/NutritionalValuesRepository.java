package com.nutrition.sweng.Repository;

import com.nutrition.sweng.Model.NutritionalValues;

import java.util.List;
import java.util.Optional;

public interface NutritionalValuesRepository {
    Optional<NutritionalValues> findById(long id);
    NutritionalValues save(NutritionalValues nv);
}
