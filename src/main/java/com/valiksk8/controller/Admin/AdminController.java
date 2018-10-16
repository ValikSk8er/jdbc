package com.valiksk8.controller.Admin;

import com.valiksk8.controller.Controller;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

public class AdminController implements Controller {

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = ViewModel.of("adminPage");
        return vm;
    }
}
