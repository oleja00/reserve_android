package com.maxkudla.reserve.data.db;

import android.content.Context;

import com.maxkudla.reserve.Constants;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmMigrationNeededException;

/**
 * Created by Developer on 22.04.2017.
 */

public class DataManager {
    private Realm mRealm;


    public void init(Context context) {
        mRealm = getRealmInstance(context);

    }

    private Realm getRealmInstance(Context context) {
        try {
            return Realm.getDefaultInstance();
        } catch (RealmMigrationNeededException exception) {
            Realm.deleteRealm(new RealmConfiguration.Builder()
                    .name(Constants.Realm.STORAGE_MAIN).build());
            return Realm.getDefaultInstance();
        }
    }
}
