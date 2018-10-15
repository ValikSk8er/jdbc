package com.valiksk8.service;

import com.valiksk8.model.User;

public interface UserService extends Service<User>{
    User addUser(User user);
    User findByEmail(String email);
    boolean validatePassword(User user, String password);
}
