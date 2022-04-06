package com.nutrition.sweng.Model;

import java.util.List;
import java.util.Optional;

public interface MineralsRepository {

    Optional<Minerals> findById(long id);
    List<Minerals> findByFoodId(long id);
    Minerals save(Minerals m);
    void delete(Minerals m);

}