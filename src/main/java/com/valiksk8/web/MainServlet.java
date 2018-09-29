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
        Request request = Request.of(req.getMethod(), req.getRequestURI());

        Controller controller = controllerMap.getOrDefault(request, getPageNotFoundController());

        ViewModel vm = controller.process(request);

        sendResponse(vm, req, resp);
        //        if(req.getMethod().equals("GET") && req.getRequestURI().equals("/servlet/home")){
//            req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
//        } else {
//            req.getRequestDispatcher("/WEB-INF/views/404.jsp").forward(req, resp);
//        }
    }

    private void sendResponse(ViewModel vm, HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String redirectUrl = "/WEB-INF/views/%s.jsp";
        vm.getModel().forEach(req:: setAttribute);
        req.getRequestDispatcher(String.format(redirectUrl, vm.getView())).forward(req, resp);

    }
}