package com.valiksk8.controller;

import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

public interface Controller {

    ViewModel process(Request request);

}
