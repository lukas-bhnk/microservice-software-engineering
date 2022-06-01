package com.nutrition.sweng.Outbound;

import com.nutrition.sweng.Model.Meal;
import com.nutrition.sweng.Model.User;
import com.nutrition.sweng.Repository.MealRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MealJpaRepository extends CrudRepository<Meal, Long>, MealRepository {
    @Query("SELECT m FROM Meal m WHERE m.date = :date AND m.userFk = :userFk")
    List<Meal> findByDateAndUser(Date date, User userFk);

    @Query("SELECT m FROM Meal m WHERE m.id = :id AND m.userFk = :userFk")
    Optional<Meal> findByIdAndUser(Long id, User userFk);
}