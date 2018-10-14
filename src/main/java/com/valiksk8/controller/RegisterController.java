package com.valiksk8.controller;

import com.valiksk8.model.User;
import com.valiksk8.service.UserService;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

public class RegisterController implements Controller {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Request request) {
        String email = request.getParamByName("email");
        String password = request.getParamByName("password");
        ViewModel vm;
        if (isRegistered(email)) {
            vm = processUnregister();
        } else {
            User user = new User(email, password);
            vm = processRegistration(user);
        }
        return vm;
    }

    private ViewModel processRegistration(User user) {
        ViewModel vm = ViewModel.of("welcome");
        vm.addAttribute("user", userService.addUser(user));
        return vm;
    }

    private ViewModel processUnregister() {
        ViewModel vm = ViewModel.of("register");
        vm.addAttribute("msg", true);
        return vm;
    }

    private boolean isRegistered(String email) {
        return userService.findByEmail(email) != null ? true : false;
    }
}
