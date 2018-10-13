package com.valiksk8.model;

import com.valiksk8.metadata.ColumnName;
import com.valiksk8.metadata.TableName;

@TableName("ROLE")
public class Role {

    @ColumnName("Name")
    private RoleName roleName;
    private User user;

    public Role() {
    }

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public static Role of(String  roleName) {
        return new Role(RoleName.valueOf(roleName));
    }

    public enum RoleName {
        USER,
        ADMIN
    }
}
