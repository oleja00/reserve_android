package com.maxkudla.reserve.models.options;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Developer on 02.05.2017.
 */

public class Datum implements ItemOptions{

    private String viewType;

    private String label;
    @SerializedName("options")
    private List<Option> options = null;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Option> getItems() {
        return options;
    }

    public void setItems(List<Option> options) {
        this.options = options;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    @Override
    public String getViewType() {
        return viewType;
    }
}
