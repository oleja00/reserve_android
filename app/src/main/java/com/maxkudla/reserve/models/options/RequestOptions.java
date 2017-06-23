package com.maxkudla.reserve.models.options;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestOptions {

    @SerializedName("data")
    private List<Datum> datums;

    private String category;

    private Location location;

    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Datum> getDatums() {
        return datums;
    }

    public void setDatums(List<Datum> datums) {
        this.datums = datums;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
