package com.nutrition.sweng.Repository;

import com.nutrition.sweng.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);
    List<User> findByName(String name);

}
