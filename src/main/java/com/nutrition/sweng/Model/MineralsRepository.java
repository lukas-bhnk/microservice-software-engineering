package com.nutrition.sweng.Model;

import java.util.List;
import java.util.Optional;

public interface MineralsRepository {

    List<Minerals> findById(long id);
    Minerals save(Minerals m);
    void delete(Minerals m);

}