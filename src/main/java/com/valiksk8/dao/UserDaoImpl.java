package com.valiksk8.dao;

import com.valiksk8.model.Role;
import com.valiksk8.model.User;
import com.valiksk8.utils.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.valiksk8.model.Role.RoleName.USER;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public UserDaoImpl(Connection connection) {
        super(connection);
    }


    @Override
    public User addUser(User user) {

        String userQuery = "INSERT INTO USERS (EMAIL, TOKEN, PASSWORD, FIRST_NAME, LAST_NAME) VALUES(?,?,?,?,?)";
        String roleQuery = "INSERT INTO USER_TO_ROLE (FK_USER_ID, FK_ROLE_ID) VALUES(?,?)";
        PreparedStatement userStatement;
        PreparedStatement roleStatement;

        try {
            connection.setAutoCommit(false);

            userStatement = connection.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS);
            userStatement.setString(1, user.getEmail());
            userStatement.setString(2, user.getToken());
            userStatement.setString(3, user.getPassword());
            userStatement.setString(4, user.getFirstName());
            userStatement.setString(5, user.getLastName());
            userStatement.executeUpdate();

            ResultSet rs = userStatement.getGeneratedKeys();
            long userId = 0;
            if(rs.next()){
                userId = rs.getLong(1);

            } else {
                connection.rollback();
            }

            for(Role role : user.getRoles()) {
                roleStatement = connection.prepareStatement(roleQuery);
                roleStatement.setLong(1, userId);
                roleStatement.setString(2, role.getRoleName().toString());
                roleStatement.executeUpdate();
                connection.commit();
            }
        } catch (SQLException e) {
            e.getMessage();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException("User was not added");
            }
        }
        return user;
    }

    @Override
    public User findByToken(String token) {
        String query = "SELECT U.ID, U.EMAIL, U.PASSWORD, U.TOKEN, U.FIRST_NAME, U.LAST_NAME, R.NAME " +
                "FROM USERS U " +
                "JOIN USER_TO_ROLE UTR ON U.ID = UTR.FK_USER_ID " +
                "JOIN ROLE R ON UTR.FK_ROLE_ID = R.NAME " +
                "WHERE U.TOKEN = ?";
        PreparedStatement statement;
        ResultSet resultSet;
        User user = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, token);
            resultSet = statement.executeQuery();
            user = resultSet.next() ? getUserWIthRolesFromResultSet(resultSet) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    private User getUserWIthRolesFromResultSet(ResultSet resultSet) throws SQLException {
        User user = getObjectFromResultSet(resultSet);
        while (!resultSet.isAfterLast()){
            Role role = Role.of(resultSet.getString(7));
            user.addRole(role);
            resultSet.next();
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        String query = QueryBuilder.getSelectByParamQuery(User.class,"EMAIL");
        return simpleConnectAndGetObjectByParam(query, email);
    }
}
