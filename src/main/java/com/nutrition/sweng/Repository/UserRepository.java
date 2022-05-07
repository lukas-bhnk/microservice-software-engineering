package com.nutrition.sweng.Repository;

import com.nutrition.sweng.Model.User;

import java.util.List;

public interface UserRepository {

    User findByEmail(String email);
    List<User> findByName(String name);

}
