package com.maxkudla.reserve.utils;

/**
 * Created by Developer on 19.04.2017.
 */

import android.text.TextUtils;

import java.io.IOException;



import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RetrofitInterceptor implements Interceptor {


    private static final String HEADER_AUTHORIZATION = "Authorization";
    private AuthTokenHolder mAuthTokenHolder;


    public RetrofitInterceptor(AuthTokenHolder authTokenHolder) {
        mAuthTokenHolder = authTokenHolder;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = mAuthTokenHolder.getToken();
        Request.Builder builder = chain.request().newBuilder();
        if (!TextUtils.isEmpty(token)) {
            builder.addHeader(HEADER_AUTHORIZATION, "Bearer  " + token);
        }
        return chain.proceed(builder.build());
    }
}
