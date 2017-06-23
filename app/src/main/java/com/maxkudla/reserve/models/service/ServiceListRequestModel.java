package com.maxkudla.reserve.models.service;

import java.util.List;

/**
 * Created by Oleja on 03.06.2017.
 */

public class ServiceListRequestModel {
    private boolean error;
    private List<ReserveService> data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ReserveService> getData() {
        return data;
    }

    public void setData(List<ReserveService> data) {
        this.data = data;
    }
}
