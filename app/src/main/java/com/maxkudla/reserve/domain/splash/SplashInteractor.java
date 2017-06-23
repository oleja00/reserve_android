package com.maxkudla.reserve.domain.splash;

import com.maxkudla.reserve.models.token.TokenResponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class SplashInteractor {

    private SplashRepository mSplashRepository;

    @Inject
    public SplashInteractor(SplashRepository splashRepository) {
        mSplashRepository = splashRepository;
    }

    public Single<TokenResponse> checkTokenRemote(){
        return mSplashRepository.checkTokenRemote();
    }

}
