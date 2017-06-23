package com.maxkudla.reserve.models.client;

public class Client {
    //client _id":"58fcfe7aaa2ddb2605ead7d9","createdAt":"2017-04-23T19:20:26.709Z","updatedAt":"2017-04-23T19:20:26.709Z","phone":"380931211095","__v":0,"type":1},

    private String _id;
    private String phone;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
