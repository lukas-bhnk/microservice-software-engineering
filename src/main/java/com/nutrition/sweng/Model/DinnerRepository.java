package com.nutrition.sweng.Model;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DinnerRepository {

    Optional<Dinner> findById(Integer id);
    List<Dinner> findByUserId(long id);
    List<Dinner> findByDate(Date d);
    Dinner save(Dinner d);
    void delete(Dinner d);
}