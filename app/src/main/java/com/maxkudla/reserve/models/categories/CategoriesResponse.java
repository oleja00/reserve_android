package com.maxkudla.reserve.models.categories;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Developer on 29.04.2017.
 */

public class CategoriesResponse {
    private boolean error;
    @SerializedName("data")
    private List<Category> categories = null;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Category> getData() {
        return categories;
    }

    public void setData(List<Category> categories) {
        this.categories = categories;
    }
}
