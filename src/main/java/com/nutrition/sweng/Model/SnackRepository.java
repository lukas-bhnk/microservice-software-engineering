package com.nutrition.sweng.Model;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SnackRepository {
    Optional<Snack> findById(Integer id);
    List<Snack> findByUserId(long id);
    List<Snack> findByDate(Date d);
    Snack save(Snack s);
    void delete(Snack s);
}
