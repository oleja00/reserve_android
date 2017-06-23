package com.maxkudla.reserve.models.cities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Developer on 29.04.2017.
 */

public class CitiesResponse {

    private boolean error;
    @SerializedName("data")
    private List<City> cities = null;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<City>  getData() {
        return cities;
    }

    public void setData(List<City>  cities) {
        this.cities = cities;
    }

}
