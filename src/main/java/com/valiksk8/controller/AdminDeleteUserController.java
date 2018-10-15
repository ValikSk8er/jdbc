package com.valiksk8.controller;

import com.valiksk8.model.Role;
import com.valiksk8.model.User;
import com.valiksk8.service.UserService;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

import java.util.Arrays;
import java.util.List;

public class AdminDeleteUserController implements Controller {
    private final  UserService userService;

    public AdminDeleteUserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = ViewModel.of("adminUsers");
        String email = request.getParamByName("email");

        boolean isNotExist = !userService.isRegistered(email);
        if (isNotExist) {
            vm.addAttribute("msg_delete_error", true);
        } else {
            processDeleteByEmail(email);
            vm.addAttribute("msg_delete_success", true);
        }
        List<Role.RoleName> roleNames = Arrays.asList(Role.RoleName.values());
        List<User> users = userService.findAll();
        vm.addAttribute("roles", roleNames);
        vm.addAttribute("users", users);
        return vm;
    }

    private void processDeleteByEmail(String email) {
        User user = userService.findByEmail(email);
        userService.deleteById(user.getId());
    }
}
