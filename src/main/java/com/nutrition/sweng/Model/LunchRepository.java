package com.nutrition.sweng.Model;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LunchRepository {

    Optional<Lunch> findById(Integer id);
    List<Lunch> findByUserId(long id);
    List<Lunch> findByDate(Date d);
    Lunch save(Lunch f);
    void delete(Lunch f);

}
