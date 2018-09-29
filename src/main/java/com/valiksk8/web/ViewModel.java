package com.valiksk8.web;

import com.valiksk8.Main;
import com.valiksk8.model.Category;

import java.util.HashMap;
import java.util.Map;

public class ViewModel {

    private final String view;
    private final Map<String, Object> model = new HashMap<>();


    public String getView() {
        return view;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public ViewModel(String view) {
        this.view = view;
    }

    public void addAttribute(String name, Object obj) {
        this.model.put(name, obj);
    }

    public static ViewModel of(String view) {
        return new ViewModel(view);
    }


}
