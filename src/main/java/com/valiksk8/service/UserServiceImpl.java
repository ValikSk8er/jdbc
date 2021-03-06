package com.valiksk8.service;

import com.valiksk8.dao.UserDao;
import com.valiksk8.model.Role;
import com.valiksk8.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.valiksk8.model.Role.RoleName.USER;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        List<User> users = userDao.findAll();
        List<User> result = new ArrayList<>();

        for (User user : users) {
            String token = user.getToken();
            user = userDao.findByToken(token);
            result.add(user);
        }
        return result;
    }

    @Override
    public void add(User user) {
        userDao.addUser(user);
    }

    @Override
    public User addUserWithRoles(User user, Set<Role> roles) {
        String hashedPassword = hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        user.setToken(getToken());
        user.setRoles(roles);
        return userDao.addUser(user);
    }

    @Override
    public User addUser(User user) {
        Set<Role> roles = new HashSet<>(Arrays.asList(getDefaultRole()));
        return addUserWithRoles(user, roles);
    }

    @Override
    public void updateById(Long id, User user) {
        userDao.updateById(id, user);
    }

    @Override
    public void deleteById(Long id) {
        userDao.clearRoleOnUserById(id);
        userDao.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean validatePassword(User user, String password) {
        boolean result = false;

        if (user != null) {
            String hashedPassword = hashPassword(password);
            result = hashedPassword.equals(user.getPassword());
        }
        return result;
    }

    @Override
    public boolean isRegistered(String email) {
        return findByEmail(email) != null;
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }

    private String hashPassword(String password) {
        MessageDigest digest = null;
        byte[] encodedHash = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return bytesToHex(encodedHash);
    }

    private String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private Role getDefaultRole() {
        return Role.of(USER.toString());
    }
}
