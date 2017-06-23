package com.maxkudla.reserve.models.token;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Developer on 23.04.2017.
 */

public class TokenRequest {
    @SerializedName("access-key")
    private String access_key;

    public String getAccess_key() {
        return access_key;
    }

    public void setAccess_key(String access_key) {
        this.access_key = access_key;
    }
}
