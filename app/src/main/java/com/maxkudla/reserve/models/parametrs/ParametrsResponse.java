package com.maxkudla.reserve.models.parametrs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Developer on 30.04.2017.
 */

public class ParametrsResponse {

    private boolean error;
    @SerializedName("data")
    private List<Parametr> parametrs = null;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Parametr> getData() {
        return parametrs;
    }

    public void setData(List<Parametr> parametrs) {
        this.parametrs = parametrs;
    }
}
