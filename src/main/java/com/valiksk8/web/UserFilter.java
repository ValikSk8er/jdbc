package com.valiksk8.web;

import com.valiksk8.dao.UserDao;
import com.valiksk8.model.Role;
import com.valiksk8.model.Role.RoleName;
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
import java.util.HashMap;
import java.util.Map;

import static com.valiksk8.Factory.getUserDao;

public class UserFilter implements Filter {

    private final Map<String, RoleName> protectedUriMap = new HashMap<>();
    private UserDao userDao;
    private static final  String COOKIE_NAME = "MATE";

    @Override
    public void init(FilterConfig filterConfig) {
        userDao = getUserDao();
        protectedUriMap.put("/servlet/categories", RoleName.USER);
        protectedUriMap.put("/servlet/category", RoleName.USER);
        protectedUriMap.put("/servlet/products", RoleName.USER);
        protectedUriMap.put("/servlet/product", RoleName.USER);
        protectedUriMap.put("/servlet/home", RoleName.USER);
        protectedUriMap.put("/servlet/admin", RoleName.ADMIN);
        protectedUriMap.put("/servlet/admin/categories", RoleName.ADMIN);
        protectedUriMap.put("/servlet/admin/products", RoleName.ADMIN);
        protectedUriMap.put("/servlet/admin/users", RoleName.ADMIN);
        protectedUriMap.put("/servlet/admin/roles", RoleName.ADMIN);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Cookie[] cookies = req.getCookies();
        String uri = req.getRequestURI();
        RoleName roleName = protectedUriMap.get(uri);
        String token = null;
        User user;

        if(roleName == null) {
            processAuthenteticated(request, response, chain);
            return;
        }

        for(Cookie c : cookies) {
            if(c.getName().equals(COOKIE_NAME)) {
                token = c.getValue();
            }

            if(token == null) {
                processUnauthenticated(request, response);
            } else {
                user = userDao.findByToken(token);
                if (user == null) {
                    processUnauthenticated(request, response);
                } else {
                    if (varifyRole(user, roleName)) {
                        req.setAttribute("user", user);
                        processAuthenteticated(request, response, chain);
                    } else {
                        processAccessDenied(request, response);
                    }
                }
            }
        }
    }

    private void processAccessDenied(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(request, response);
    }

    private boolean varifyRole(User user, RoleName roleName) {
        return user.getRoles().stream()
                .map(Role::getRoleName)
                .anyMatch(r -> r.equals(roleName));
//        .filter(r -> r.equals(roleName))
//                .findFirst()
//                .isPresent();
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
