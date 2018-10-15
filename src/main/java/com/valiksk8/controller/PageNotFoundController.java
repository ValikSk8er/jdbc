package com.valiksk8.controller;

import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

public class PageNotFoundController implements Controller {
    @Override
    public ViewModel process(Request request) {
        return ViewModel.of("404");
    }
}
