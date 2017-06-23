package com.maxkudla.reserve.utils;

import io.realm.RealmObject;

public class RealmString extends RealmObject {
    private String mString;

    public RealmString() {
    }

    public String getString() {
        return mString;
    }

    public void setString(String string) {
        mString = string;
    }

    public RealmString(String string) {
        mString = string;
    }
}
