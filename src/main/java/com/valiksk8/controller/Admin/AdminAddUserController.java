package com.valiksk8.controller.Admin;

import com.valiksk8.controller.Controller;
import com.valiksk8.model.Role;
import com.valiksk8.model.User;
import com.valiksk8.service.UserService;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdminAddUserController implements Controller {
    private final UserService userService;

    public AdminAddUserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = ViewModel.of("adminUsers");
        String email = request.getParamByName("email");

        boolean isExist = userService.isRegistered(email);
        if (isExist) {
            vm.addAttribute("msg_error", true);
        } else {
            processAddUser(request);
            vm.addAttribute("msg_success", true);
        }

        List<Role.RoleName> roleNames = Arrays.asList(Role.RoleName.values());
        List<User> users = userService.findAll();
        vm.addAttribute("roles", roleNames);
        vm.addAttribute("users", users);
        return vm;
    }

    private void processAddUser(Request request) {
        String email = request.getParamByName("email");
        String password = request.getParamByName("password");
        String firstName = request.getParamByName("firstName");
        String lastName = request.getParamByName("lastName");

        User user = new User(email, password, firstName, lastName);
        Set<Role> roles = getRolesFromRequest(request);

        if (roles.isEmpty()) {
            userService.addUser(user);
        } else {
            userService.addUserWithRoles(user, roles);
        }
    }

    private Set<Role> getRolesFromRequest(Request request) {
        Set<Role> roles = new HashSet<>();

        for(Role.RoleName role : Role.RoleName.values()) {
            boolean reqContainRole = request.getParamByName(role.toString()) != null;
            if (reqContainRole) {
                roles.add(new Role(role));
            }
        }
        return roles;
    }
}
