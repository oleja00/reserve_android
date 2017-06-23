package com.maxkudla.reserve.models.login;

/**
 * Created by Developer on 22.04.2017.
 */

public class LoginResponse {

    private boolean error;

    private LoginData data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }
}
