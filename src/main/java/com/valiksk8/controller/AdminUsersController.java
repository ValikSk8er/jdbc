package com.valiksk8.controller;

import com.valiksk8.model.Role;
import com.valiksk8.model.User;
import com.valiksk8.service.UserService;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

import java.util.Arrays;
import java.util.List;

public class AdminUsersController implements Controller {
    private final UserService userService;

    public AdminUsersController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = ViewModel.of("adminUsers");
        List<Role.RoleName> roles = Arrays.asList(Role.RoleName.values());
        List<User> users = userService.findAll();

        vm.addAttribute("roles", roles);
        vm.addAttribute("users", users);
        return vm;
    }
}
