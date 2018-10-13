package com.valiksk8.controller;

import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

import javax.servlet.http.Cookie;

public class LogoutController implements Controller {


    @Override
    public ViewModel process(Request request) {
        Cookie[] cookies = request.getCookies();
        ViewModel vm = ViewModel.of("login");
        if(cookies == null) {
            return vm;
        }
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equals("MATE")) {
                cookie.setMaxAge(0);
                vm.setCookie(cookie);
            }
        }
        return vm;
    }
}
