package com.maxkudla.reserve.domain.login;

import com.maxkudla.reserve.models.login.LoginRequest;
import com.maxkudla.reserve.models.login.LoginResponse;

import io.reactivex.Observable;

public interface LoginRepository {

    Observable<LoginResponse> registerUserRemote(LoginRequest loginRequest);

}
