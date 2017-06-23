package com.maxkudla.reserve.data.login;

import com.maxkudla.reserve.data.api.RetrofitManager;
import com.maxkudla.reserve.di.activity.ActivityScope;
import com.maxkudla.reserve.domain.login.LoginRepository;
import com.maxkudla.reserve.models.login.LoginRequest;
import com.maxkudla.reserve.models.login.LoginResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class LoginRepositoryImpl implements LoginRepository {

    private RetrofitManager mRetrofitManager;

    @Inject
    public LoginRepositoryImpl(RetrofitManager retrofitManager) {
        mRetrofitManager = retrofitManager;
    }

    @Override
    public Observable<LoginResponse> registerUserRemote(LoginRequest loginRequest) {
        return mRetrofitManager.registerUserRemote(loginRequest);
    }
}
