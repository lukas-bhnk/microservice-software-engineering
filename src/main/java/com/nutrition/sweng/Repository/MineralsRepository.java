package com.nutrition.sweng.Repository;

import com.nutrition.sweng.Model.Minerals;

import java.util.Optional;

public interface MineralsRepository {

    Optional<Minerals> findById(long id);
    Minerals save(Minerals m);
    void delete(Minerals m);

}