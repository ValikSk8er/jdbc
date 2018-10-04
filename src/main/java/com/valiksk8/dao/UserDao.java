package com.valiksk8.dao;

import com.valiksk8.model.User;

public interface UserDao {
    public User addUser(User user);
    public User findByEmail(String email);

    User findByToken(String token);
}
