package com.maxkudla.reserve.models.options;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Developer on 02.05.2017.
 */

public class Option implements ItemOptions{

    private String viewType;

    private String type;
    private String title;
    private String field;
    private boolean value;
    @SerializedName("items")
    private List<Item> items = null;

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    @Override
    public String getViewType() {
        return viewType;
    }
}
