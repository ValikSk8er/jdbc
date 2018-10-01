package com.valiksk8.controller;

import com.valiksk8.service.UserService;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

import java.sql.SQLException;

public class LoginController implements Controller {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Request request) throws SQLException {
        return null;
    }
}
