package com.nutrition.sweng.Repository;

import com.nutrition.sweng.Model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);
    User save(User u);
    void delete(User u);
}
