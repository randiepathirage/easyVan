package com.e.esayVan;

public class User {
    String password;
    String name;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String password, String name) {
        this.password = password;
        this.name = name;
    }
}
