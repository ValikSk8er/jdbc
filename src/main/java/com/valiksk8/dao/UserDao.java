package com.valiksk8.dao;

import com.valiksk8.model.User;

public interface UserDao {
    User addUser(User user);
    User findByEmail(String email);

    User findByToken(String token);
}
