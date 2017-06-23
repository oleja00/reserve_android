package com.maxkudla.reserve.models.login;

/**
 * Created by Developer on 22.04.2017.
 */

public class LoginUser {

    private String _id;
    private long phone;
    private int type;

    public String getId() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
