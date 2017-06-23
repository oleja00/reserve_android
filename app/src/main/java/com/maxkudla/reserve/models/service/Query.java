package com.maxkudla.reserve.models.service;


public class Query {

    // "query":{"_id":"5921ea8c3782e719212939b5","createdAt":"2017-05-21T19:29:16.237Z","updatedAt":"2017-05-21T19:29:16.237Z","client":"58fcfe7aaa2ddb2605ead7d9","options":{"worktill":24,"smoking":"no_smoking","hookah":false,"cuisines":["58f9e0aa7866391ab6f554bc"]},"location":{"latitude":48.51660399999999,"longitude":35.04638699999998},"distance":0.5,"guests":2,"category":"58f8c570e2d087083269971c","__v":0},
    private String _id;
    private double distance;
    private int guests;
    private String note;
    private Location location;
    private QueryOptions options;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public QueryOptions getOptions() {
        return options;
    }

    public void setOptions(QueryOptions options) {
        this.options = options;
    }
}
