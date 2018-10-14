package com.valiksk8.controller;

import com.valiksk8.dao.CategoryDao;
import com.valiksk8.dao.CategoryDaoImpl;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

import java.sql.SQLException;

public class GetAdminDeleteCategoryContorller implements Controller {

    CategoryDao categoryDao;

    public GetAdminDeleteCategoryContorller(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public ViewModel process(Request request) throws SQLException {
        return null;
    }
}
