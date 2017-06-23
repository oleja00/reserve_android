package com.maxkudla.reserve.presenter.login;

import android.util.Log;
import android.util.Patterns;

import com.google.firebase.iid.FirebaseInstanceId;
import com.maxkudla.reserve.App;
import com.maxkudla.reserve.domain.login.RegisterUserInteractor;
import com.maxkudla.reserve.models.login.LoginRequest;
import com.maxkudla.reserve.presenter.base.BasePresenter;

import java.util.regex.Matcher;

import javax.inject.Inject;

import io.intercom.android.sdk.Intercom;
import io.intercom.android.sdk.identity.Registration;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.maxkudla.reserve.Constants.USER_CLIENT;


/**
 * Created by Developer on 19.04.2017.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View, LoginRouter>
        implements LoginContract.EventDelegate/*, AuthCallback*/ {

    private RegisterUserInteractor mRegisterUserInteractor;

    @Inject
    LoginPresenter(RegisterUserInteractor registerUserInteractor) {
        mRegisterUserInteractor = registerUserInteractor;
    }

    public void login(String phone) {
        //   Digits.logout();
//        AuthConfig.Builder authConfigBuilder = new AuthConfig.Builder()
//                .withAuthCallBack(this)
//                .withPhoneNumber(phone);
//
//        Digits.authenticate(authConfigBuilder.build());

        Matcher matcher = Patterns.PHONE.matcher(phone);
        if(matcher.matches()){
            Registration registration = Registration.create().withUserId(phone);
            Intercom.client().registerIdentifiedUser(registration);
            register(phone);
        }else {
            getView().showNumberError();
        }


    }

//    @Override
//    public void success(DigitsSession session, String phoneNumber) {
//        register(phoneNumber);
//    }

//    @Override
//    public void failure(DigitsException error) {
//
//    }
    @Override
    protected void onDetachView() {
        super.onDetachView();
    }

    private void register(String phoneNumber) {
        Log.d("TokenTag", phoneNumber);
        final String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        // TODO: for some reason phone always full of number
        long phone = Long.parseLong(phoneNumber.substring(1));
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPhone(phone);
        loginRequest.setToken(refreshedToken);

        manageDisposable(mRegisterUserInteractor.execute(loginRequest)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResponse -> {
                    Log.d("ResponseTag", loginResponse.getData().getToken());
                    App.getAuthTokenHolder().setToken(loginResponse.getData().getToken());
                    App.getAuthTokenHolder().setPhone(phoneNumber);
                    if(loginResponse.getData().getUser().getType()==USER_CLIENT){
                        getRouter().launchToMainScreen();
                    }else {
                        getRouter().launchToSocketScreen(loginResponse.getData().getUser().getType(), loginResponse.getData().getUser().getId());
                    }


                }, Throwable::printStackTrace));
    }


}
