package com.maxkudla.reserve.presenter.login;

/**
 * Created by Developer on 29.04.2017.
 */

public interface LoginRouter {
    void launchToMainScreen();
    void launchToSocketScreen(int type);
    void launchToSocketScreen(int type, String serviceId);
}
