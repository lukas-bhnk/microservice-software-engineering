package com.nutrition.sweng.Model;

import java.util.List;
import java.util.Optional;

public interface NutritionalValuesRepository {
    Optional<NutritionalValues> findById(long id);
    List<NutritionalValues> findByFoodId(long id);
    NutritionalValues save(NutritionalValues nv);
    void delete(NutritionalValues nv);
}
