package com.maxkudla.reserve.utils;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

public class RealmInt extends RealmObject implements Parcelable{
    private double mInt;

    public RealmInt() {
    }

    public RealmInt(double anInt) {
        mInt = anInt;
    }

    protected RealmInt(Parcel in) {
        mInt = in.readDouble();
    }

    public static final Creator<RealmInt> CREATOR = new Creator<RealmInt>() {
        @Override
        public RealmInt createFromParcel(Parcel in) {
            return new RealmInt(in);
        }

        @Override
        public RealmInt[] newArray(int size) {
            return new RealmInt[size];
        }
    };

    public double getInt() {
        return mInt;
    }

    public void setInt(double anInt) {
        mInt = anInt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(mInt);
    }
}
