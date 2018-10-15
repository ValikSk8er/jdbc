package com.valiksk8;

import com.valiksk8.controller.AdminAddUserController;
import com.valiksk8.controller.AdminUsersController;
import com.valiksk8.controller.Controller;
import com.valiksk8.controller.AdminAddCategoryContorller;
import com.valiksk8.controller.AdminCategoriesController;
import com.valiksk8.controller.AdminDeleteCategoryContorller;
import com.valiksk8.controller.AllCategoriesController;
import com.valiksk8.controller.CategoryByIdController;
import com.valiksk8.controller.ProductByIdController;
import com.valiksk8.controller.LoginController;
import com.valiksk8.controller.LogoutController;
import com.valiksk8.controller.PageNotFoundController;
import com.valiksk8.controller.RegisterController;
import com.valiksk8.dao.CategoryDaoImpl;
import com.valiksk8.dao.ProductDaoImpl;
import com.valiksk8.dao.UserDaoImpl;
import com.valiksk8.service.CategoryServiceImpl;
import com.valiksk8.service.ProductServiceImpl;
import com.valiksk8.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Factory {
    static Connection connection = null;

    static {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static PageNotFoundController getPageNotFoundController() { return new PageNotFoundController(); }

    public static UserDaoImpl getUserDao() {
        return new UserDaoImpl(connection);
    }

    public static ProductDaoImpl getProductDao() {
        return new ProductDaoImpl(connection);
    }

    public static CategoryDaoImpl getCategoryDao() { return new CategoryDaoImpl(connection); }

    public static UserServiceImpl getUserService() {
        return new UserServiceImpl(getUserDao());
    }

    public static ProductServiceImpl getProductService() { return new ProductServiceImpl(getProductDao()); }

    public static CategoryServiceImpl getCategoryService() { return new CategoryServiceImpl(getCategoryDao()); }

    public static AllCategoriesController getAllCategoriesController() {
        return new AllCategoriesController(getCategoryService());
    }

    public static CategoryByIdController getCategoryByIdController() {
        return new CategoryByIdController(getCategoryService());
    }

    public static ProductByIdController getProductByIdController() {
        return new ProductByIdController(getProductService());
    }

    public static LoginController getLoginPageController() {
        return new LoginController(getUserService());
    }

    public static RegisterController getRegisterController() {
        return new RegisterController(getUserService());
    }

    public static Controller getLogoutController() {
        return new LogoutController();
    }

    public static Controller getAdminAddCategoryContorller() {
        return new AdminAddCategoryContorller(getCategoryService());
    }

    public static Controller getAdminDeleteCategoryContorller() {
        return new AdminDeleteCategoryContorller(getCategoryService());
    }

    public static Controller getAdminCategoriesContorller() {
        return new AdminCategoriesController(getCategoryService());
    }

    public static Controller getAdminUsersController() {
        return new AdminUsersController(getUserService());
    }

    public static Controller getAdminAddUserController() {
        return new AdminAddUserController(getUserService());
    }
}