package com.maxkudla.reserve.presenter.splash;

/**
 * Created by Developer on 29.04.2017.
 */

public interface SplashRouter {

    void launchToLoginScreen();
    void launchToMainScreen();

    void launchToSocketScreen(String serviceId, int type);
    void launchToSocketScreen(int type, String status);

}
