package com.valiksk8.dao;

import com.valiksk8.model.User;

public interface UserDao extends Dao<User>{
    User addUser(User user);
    User findByEmail(String email);

    User findByToken(String token);
}
