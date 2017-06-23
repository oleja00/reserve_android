package com.maxkudla.reserve.data.splash;

import com.maxkudla.reserve.data.api.RetrofitManager;
import com.maxkudla.reserve.di.activity.ActivityScope;
import com.maxkudla.reserve.domain.splash.SplashRepository;
import com.maxkudla.reserve.models.token.TokenResponse;

import javax.inject.Inject;

import io.reactivex.Single;

@ActivityScope
public class SplashRepositoryImpl implements SplashRepository {

    private RetrofitManager mRetrofitManager;

    @Inject
    SplashRepositoryImpl (RetrofitManager retrofitManager){
        mRetrofitManager = retrofitManager;
    }

    @Override
    public Single<TokenResponse> checkTokenRemote() {
        return mRetrofitManager.checkTokenRemote();
    }
}
