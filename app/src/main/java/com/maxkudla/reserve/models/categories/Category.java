package com.maxkudla.reserve.models.categories;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Developer on 29.04.2017.
 */

public class Category {

    @SerializedName("_id")
    private String id;

    private String name;

    private String image;

    private int count;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

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
