package com.valiksk8.controller.Admin;

import com.valiksk8.controller.Controller;
import com.valiksk8.model.Role;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

import java.util.Arrays;
import java.util.List;

public class AdminRolesController implements Controller {

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = ViewModel.of("adminRoles");
        List<Role.RoleName> roleNames = Arrays.asList(Role.RoleName.values());
        vm.addAttribute("roles", roleNames);
        return vm;
    }
}
