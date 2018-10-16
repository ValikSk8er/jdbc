package com.valiksk8.service;

import com.valiksk8.model.Role;
import com.valiksk8.model.User;

import java.util.Set;

public interface UserService extends Service<User>{
    User addUserWithRoles(User user, Set<Role> roles);
    User addUser(User user);
    User findByEmail(String email);
    boolean validatePassword(User user, String password);
    boolean isRegistered(String email);
    void clearRoleOnUserById(Long id);
    void deleteByEmail(String email);
}
