package com.maxkudla.reserve.models.client;

import java.util.List;

public class ClientListRequestModel {

    private boolean error;
    private List<ReserveClient> data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ReserveClient> getData() {
        return data;
    }

    public void setData(List<ReserveClient> data) {
        this.data = data;
    }
}
