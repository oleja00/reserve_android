package com.maxkudla.reserve.models.client;

import com.maxkudla.reserve.models.client.offer.Offer;

import java.util.List;

public class ReserveClient {
    private long __v;
    private String created_at;
    private String updated_at;
    private double distance;
    private String _id;
    private String status;
    private Client client;
    private Service service;
    private String query;
    private List<Offer> offer;

    public long get__v() {
        return __v;
    }

    public void set__v(long __v) {
        this.__v = __v;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public List<Offer> getOffer() {
        return offer;
    }

    public void setOffer(List<Offer> offer) {
        this.offer = offer;
    }

    @Override
    public String toString(){
        return "status " + status + " _id " + _id + " createdAt " + created_at + " updatedAt " + updated_at + " distance " + distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReserveClient)) return false;

        ReserveClient that = (ReserveClient) o;

        return get_id().equals(that.get_id());

    }

    @Override
    public int hashCode() {
        return get_id().hashCode();
    }
}
