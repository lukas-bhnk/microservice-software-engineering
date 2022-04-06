
package com.nutrition.sweng.Model;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BreakfastRepository {
    Optional<Breakfast> findById(Integer id);
    List<Breakfast> findByUserId(long id);
    List<Breakfast> findByDate(Date d);
    Breakfast save(Breakfast f);
    void delete(Breakfast f);
}
