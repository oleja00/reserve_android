package com.maxkudla.reserve.domain.splash;

import com.maxkudla.reserve.models.token.TokenResponse;

import io.reactivex.Single;

public interface SplashRepository {

    Single<TokenResponse> checkTokenRemote();

}
