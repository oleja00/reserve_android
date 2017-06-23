package com.maxkudla.reserve.models.login;

/**
 * Created by Developer on 22.04.2017.
 */

public class LoginData {

    private LoginUser user;

    private String token;

    public LoginUser getUser() {
        return user;
    }

    public void setUser(LoginUser user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
