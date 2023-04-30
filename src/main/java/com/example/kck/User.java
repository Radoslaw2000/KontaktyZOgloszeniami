package com.example.kck;

public class User {
    private String login;
    private boolean isAdmin;

    public User(String login) {
        this.login = login;
        this.isAdmin = false;
    }
    public User(String login, boolean isAdmin) {
        this.login = login;
        this.isAdmin = isAdmin;
    }

    public String getLogin() {
        return login;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
