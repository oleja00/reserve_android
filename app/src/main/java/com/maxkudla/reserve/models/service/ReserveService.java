package com.maxkudla.reserve.models.service;

public class ReserveService {
    private long __v;
    private String createdAt;
    private String updatedAt;
    private double distance;
    private String _id;
    private String status;
    private Client client;
    private Service service;
    private Query query;

    public long get__v() {
        return __v;
    }

    public void set__v(long __v) {
        this.__v = __v;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    @Override
    public String toString(){
        return "status " + status + " _id " + _id + " createdAt " + createdAt + " updatedAt " + updatedAt + " distance " + distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReserveService)) return false;

        ReserveService that = (ReserveService) o;

        return get_id().equals(that.get_id());

    }

    @Override
    public int hashCode() {
        return get_id().hashCode();
    }
}
