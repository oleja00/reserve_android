package com.maxkudla.reserve.domain.login;

import com.maxkudla.reserve.domain.baseinteractor.BaseObservableInteractor;
import com.maxkudla.reserve.models.login.LoginRequest;
import com.maxkudla.reserve.models.login.LoginResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RegisterUserInteractor extends BaseObservableInteractor<LoginResponse,LoginRequest>{

    private LoginRepository mLoginRepository;

    @Inject
    public RegisterUserInteractor(LoginRepository mLoginRepository) {
        this.mLoginRepository = mLoginRepository;
    }

//    public Observable<LoginResponse> registerUserRemote(LoginRequest loginRequest) {
//        return mLoginRepository.registerUserRemote(loginRequest);
//    }

    @Override
    protected Observable<LoginResponse> getObservable(LoginRequest loginRequest) {
        return mLoginRepository.registerUserRemote(loginRequest);
    }
}
