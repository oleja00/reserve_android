package com.maxkudla.reserve.models.login;

/**
 * Created by Developer on 22.04.2017.
 */

public class LoginRequest {
    private long phone;
    private String token;

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
