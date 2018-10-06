package com.valiksk8.web;

import com.valiksk8.dao.UserDao;
import com.valiksk8.model.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static com.valiksk8.Factory.getUserDao;

public class UserFilter implements Filter {

    private final Set<String> protectedUriSet = new HashSet<>();
    private UserDao userDao;
    private static final  String COOKIE_NAME = "MATE";

    @Override
    public void init(FilterConfig filterConfig) {
        userDao = getUserDao();
        protectedUriSet.add("/servlet/categories");
        protectedUriSet.add("/servlet/category");
        protectedUriSet.add("/servlet/products");
        protectedUriSet.add("/servlet/product");
        protectedUriSet.add("/servlet/home");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Cookie[] cookies = req.getCookies();
        String token = null;
        User user;

        if(!protectedUriSet.contains(req.getRequestURI())) {
            processAuthenteticated(request, response, chain);
        }

        for(Cookie c : cookies) {
            if(c.getName().equals(COOKIE_NAME)) {
                token = c.getValue();
            }
        }

        if(token == null) {
            processUnauthenticated(request, response);

        } else {
            user = userDao.findByToken(token);
            if (user == null) {
                processUnauthenticated(request, response);
            } else {
                req.setAttribute("user_id", user.getId());
                processAuthenteticated(request, response, chain);
            }
        }
    }

    private void processAuthenteticated(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    private void processUnauthenticated(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    public void destroy() {

    }
}
