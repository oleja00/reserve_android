package com.maxkudla.reserve.presenter.splash;

import com.maxkudla.reserve.App;
import com.maxkudla.reserve.Constants;
import com.maxkudla.reserve.domain.splash.SplashInteractor;
import com.maxkudla.reserve.presenter.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Developer on 23.04.2017.
 */

class SplashPresenter extends BasePresenter<SplashContract.View, SplashRouter>
        implements SplashContract.EventDelegate {

    private SplashInteractor mSplashInteractor;

    @Inject
    SplashPresenter(SplashInteractor splashInteractor) {
        mSplashInteractor = splashInteractor;
    }

    @Override
    protected void onDetachView() {
        super.onDetachView();
    }

    void launchToNextScreen() {

        if (App.getAuthTokenHolder().getToken() == null) {
            getRouter().launchToLoginScreen();
        } else {

            manageDisposable(mSplashInteractor.checkTokenRemote()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(tokenResponse -> {
//                        getRouter().launchToSocketScreen(0);
                        if (tokenResponse.isError()) {
                            getRouter().launchToLoginScreen();
                        } else {

                            switch (tokenResponse.getData().getType()) {
                                case Constants.USER_CLIENT:
                                    if(tokenResponse.getData().getStatus()== null){
                                        getRouter().launchToMainScreen();
                                    }else {
                                        getRouter().launchToSocketScreen(tokenResponse.getData().getType(), tokenResponse.getData().getStatus());
                                    }
                                    break;
                                case Constants.USER_SERVICE:
                                    getRouter().launchToSocketScreen(tokenResponse.getData().getService_id(), tokenResponse.getData().getType());
                                    break;
                            }

                        }

                        getView().finish();
                    }, Throwable::printStackTrace));
        }
    }

}
