package com.e.esayVan;

public class User {

    String username;
    String userRole;

    public User(String username, String userRole) {
        this.username = username;
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserRole() {
        return userRole;
    }


    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
