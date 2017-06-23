package com.maxkudla.reserve.utils;

/**
 * Created by Developer on 19.04.2017.
 */


import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;


public class AuthTokenHolder {

    private static final String KEY_AUTH_TOKEN = "auth_token";
    private static final String KEY_AUTH_PHONE = "phone";

    private SharedPreferences mSharedPreferences;

    public AuthTokenHolder(Context context) {
        mSharedPreferences = context.getSharedPreferences("APP_PREFERENCES", Context.MODE_PRIVATE);
    }

    @Nullable
    public String getToken() {
        return mSharedPreferences.getString(KEY_AUTH_TOKEN, null);
    }

    @Nullable
    public String getPhone() {
        return mSharedPreferences.getString(KEY_AUTH_PHONE, null);
    }

    public void setToken(String token) {
        mSharedPreferences.edit().putString(KEY_AUTH_TOKEN, token).apply();
    }

    public void setPhone(String phone){
        mSharedPreferences.edit().putString(KEY_AUTH_PHONE, phone).apply();
    }
}
