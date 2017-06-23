package com.maxkudla.reserve.models.options;

/**
 * Created by Developer on 02.05.2017.
 */

public class Item implements ItemOptions {

private String viewType;

    private String value;
    private String title;
    private boolean selected;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    @Override
    public String getViewType() {
        return viewType;
    }

}
