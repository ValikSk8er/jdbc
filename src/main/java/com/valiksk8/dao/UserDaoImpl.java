package com.valiksk8.dao;

import com.valiksk8.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected User getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
}
