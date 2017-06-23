package com.maxkudla.reserve.models.cities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Developer on 29.04.2017.
 */

public class City {
    @SerializedName("_id")
    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
