package com.nutrition.sweng.Model;

import java.util.Optional;

public interface MineralsRepository {

    Optional<Minerals> findById(long id);
    Minerals save(Minerals m);
    void delete(Minerals m);

}