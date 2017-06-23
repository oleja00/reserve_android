package com.maxkudla.reserve.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;


import java.io.IOException;

import io.realm.RealmList;

public class RealmIntListTypeAdapter extends TypeAdapter<RealmList<RealmInt>> {

    public static final TypeAdapter<RealmList<RealmInt>> INSTANCE = new RealmIntListTypeAdapter().nullSafe();

    private RealmIntListTypeAdapter() {
    }

    @Override
    public void write(JsonWriter out, RealmList<RealmInt> src) throws IOException {
        out.beginArray();

        for (RealmInt realmString : src) {
            out.value(realmString.getInt());
        }

        out.endArray();
    }

    @Override
    public RealmList<RealmInt> read(JsonReader in) throws IOException {
        RealmList<RealmInt> realmStrings = new RealmList<>();

        in.beginArray();

        while (in.hasNext()) {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
            } else {
                realmStrings.add(new RealmInt(in.nextDouble()));
            }
        }

        in.endArray();

        return realmStrings;
    }
}