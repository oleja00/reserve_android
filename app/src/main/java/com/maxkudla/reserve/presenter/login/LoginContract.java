package com.maxkudla.reserve.presenter.login;

/**
 * Created by Developer on 19.04.2017.
 */

public interface LoginContract {
    interface View {
        void showNumberError();
    }

    interface EventDelegate {
       void login(String  phone);
    }
}
