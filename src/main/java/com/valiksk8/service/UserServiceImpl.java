package com.valiksk8.service;

import com.valiksk8.dao.UserDao;
import com.valiksk8.model.User;

import java.util.UUID;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User addUser(User user) {
        user.setToken(getToken());
        return userDao.addUser(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean validatePassword(User user, String password) {
        //findByEmail(user.getEmail());
        return false;
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }
}
