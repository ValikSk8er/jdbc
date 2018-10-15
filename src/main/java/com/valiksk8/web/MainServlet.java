package com.valiksk8.web;

import com.valiksk8.Factory;
import com.valiksk8.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.valiksk8.Factory.getPageNotFoundController;

public class MainServlet extends HttpServlet {

    private final static Map<Request, Controller> controllerMap = new HashMap<>();

    static {
        controllerMap.put(Request.of("GET", "/servlet/categories"), Factory.getAllCategoriesController());
        controllerMap.put(Request.of("GET", "/servlet/category"), Factory.getCategoryByIdController());
        controllerMap.put(Request.of("GET", "/servlet/login"), r -> ViewModel.of("login"));
        controllerMap.put(Request.of("GET", "/servlet/register"), r -> ViewModel.of("register"));
        controllerMap.put(Request.of("GET", "/servlet/home"), r -> ViewModel.of("home"));
        controllerMap.put(Request.of("GET", "/servlet/product"), Factory.getProductByIdController());
        controllerMap.put(Request.of("GET", "/servlet/logout"), Factory.getLogoutController());
        controllerMap.put(Request.of("GET", "/servlet/admin"), r -> ViewModel.of("adminPage"));
        controllerMap.put(Request.of("GET", "/servlet/adminCategories"), Factory.getAdminCategoriesContorller());
        controllerMap.put(Request.of("GET", "/servlet/adminProducts"), r -> ViewModel.of("adminProducts"));
        controllerMap.put(Request.of("GET", "/servlet/adminUsers"), Factory.getAdminUsersController());
        controllerMap.put(Request.of("GET", "/servlet/adminRoles"), r -> ViewModel.of("adminRoles"));
        controllerMap.put(Request.of("POST", "/servlet/login"), Factory.getLoginPageController());
        controllerMap.put(Request.of("POST", "/servlet/register"), Factory.getRegisterController());
        controllerMap.put(Request.of("POST", "/servlet/addCategory"), Factory.getAdminAddCategoryContorller());
        controllerMap.put(Request.of("POST", "/servlet/deleteCategory"), Factory.getAdminDeleteCategoryContorller());
        controllerMap.put(Request.of("POST", "/servlet/adminAddUser"), Factory.getAdminAddUserController());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            process(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            process(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException {
        Request request = Request.of(req.getMethod(), req.getRequestURI(), req.getParameterMap(), req.getCookies());

        Controller controller = controllerMap.getOrDefault(request, getPageNotFoundController());

        ViewModel vm = controller.process(request);

        sendResponse(vm, req, resp);

    }

    private void sendResponse(ViewModel vm, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectUrl = "/WEB-INF/views/%s.jsp";
//        vm.getModel().forEach(req:: setAttribute);
        vm.getModel().forEach((k, v) -> req.setAttribute(k, v));
        addCockie(vm, resp);
        req.getRequestDispatcher(String.format(redirectUrl, vm.getView())).forward(req, resp);

    }

    private void addCockie(ViewModel vm, HttpServletResponse response) {
        if (vm.getCookie() != null) {
            response.addCookie(vm.getCookie());
        }
    }
}