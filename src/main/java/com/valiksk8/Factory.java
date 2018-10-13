package com.valiksk8;

import com.valiksk8.controller.Controller;
import com.valiksk8.controller.GetAllCategoriesController;
import com.valiksk8.controller.GetCategoryByIdController;
import com.valiksk8.controller.GetProductByIdController;
import com.valiksk8.controller.LoginController;
import com.valiksk8.controller.LogoutController;
import com.valiksk8.controller.PageNotFoundController;
import com.valiksk8.controller.RegisterController;
import com.valiksk8.dao.CategoryDaoImpl;
import com.valiksk8.dao.ProductDaoImpl;
import com.valiksk8.dao.UserDaoImpl;
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

    public static GetAllCategoriesController getAllCategoriesController() {
        return new GetAllCategoriesController(getCategoryDaoIml(getConnection()));
    }

    public static CategoryDaoImpl getCategoryDaoIml(Connection connection) {
        return new CategoryDaoImpl(connection);
    }

    public static PageNotFoundController getPageNotFoundController() {
        return new PageNotFoundController();
    }

    public static GetCategoryByIdController getGetCategoryByIdController() {
        return new GetCategoryByIdController(getCategoryDaoIml(getConnection()));
    }

    public static LoginController getLoginPageController() {
        return new LoginController(getUserService());
    }
    public static UserServiceImpl getUserService() {
        return new UserServiceImpl(getUserDao());
    }

    public static RegisterController getRegisterController() {
        return new RegisterController(getUserService());
    }

    public static UserDaoImpl getUserDao() {
        return new UserDaoImpl(connection);
    }

    public static ProductDaoImpl getProductDaoImpl(Connection connection) {
        return new ProductDaoImpl(connection);
    }

    public static GetProductByIdController getGetProductByIdController() {
        return new GetProductByIdController(getProductDaoImpl(connection));
    }

    public static Controller getLogoutController() {
        return new LogoutController();
    }
}
