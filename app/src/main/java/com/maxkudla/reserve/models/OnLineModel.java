package com.maxkudla.reserve.models;

/**
 * Created by Oleja on 18.06.2017.
 */

public class OnLineModel {
    private String serviceId;
    private boolean isOnline;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
