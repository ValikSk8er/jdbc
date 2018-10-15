package com.valiksk8.controller;

import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

import java.sql.SQLException;

public class GetAdminController implements Controller {


    @Override
    public ViewModel process(Request request) throws SQLException {
        ViewModel vm = ViewModel.of("adminPage");
        return vm;
    }
}
