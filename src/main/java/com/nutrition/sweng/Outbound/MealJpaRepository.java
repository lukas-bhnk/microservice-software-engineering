package com.nutrition.sweng.Outbound;

import com.nutrition.sweng.Model.Meal;
import com.nutrition.sweng.Model.User;
import com.nutrition.sweng.Repository.MealRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MealJpaRepository extends CrudRepository<Meal, Long>, MealRepository {
    @Query("SELECT m FROM Meal m WHERE m.date = :date AND m.userFk = :userFk")
    List<Meal> findByDateAndUser(Date date, User userFk);
}