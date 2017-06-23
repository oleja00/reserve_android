package com.maxkudla.reserve.models.options;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Developer on 02.05.2017.
 */

public class OptionsResponse {


    private boolean error;
    @SerializedName("data")
    private List<Datum> data = null;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
