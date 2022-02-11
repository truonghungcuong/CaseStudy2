package com.codegym.controller.leaderlogin;

public class LeaderLogin {
    private final String USERNAME="admin";
    private final String PASSWORD="123456";

    public boolean checkLoginLeader(String username,String password) {
        boolean isLogin = false;
        if (username.equals(USERNAME) && PASSWORD.equals(password)){
            isLogin=true;
        }
        return isLogin;
    }
}
