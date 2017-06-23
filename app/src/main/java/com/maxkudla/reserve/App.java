package com.maxkudla.reserve;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.crashlytics.android.Crashlytics;
import com.digits.sdk.android.Digits;
import com.google.firebase.FirebaseApp;
import com.maxkudla.reserve.data.db.DataManager;
import com.maxkudla.reserve.di.components.AppComponent;
import com.maxkudla.reserve.di.components.DaggerAppComponent;
import com.maxkudla.reserve.di.modules.AppModule;
import com.maxkudla.reserve.di.modules.DataModule;
import com.maxkudla.reserve.utils.AuthTokenHolder;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;
import io.intercom.android.sdk.Intercom;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Developer on 19.04.2017.
 */

public class App extends MultiDexApplication {
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "j98jfcKqVtPROhbqdBux9tCgi";
    private static final String TWITTER_SECRET = "lveUmcqTyMHAtQYiASDFrxlkZftxWfcGHuxgOknKHBcY6vtdC2";
    private static AppComponent mAppComponent;

    private static DataManager sDataManager;
    private static AuthTokenHolder authTokenHolder;
    @Override
    public void onCreate() {
        super.onCreate();
        Intercom.initialize(this, "android_sdk-1b1ba4091e0df27d12a29f07c6ab54fdaae7949a", "m267xfcb");
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().build(), new Crashlytics());
        Realm.init(getApplicationContext());
        authTokenHolder = new AuthTokenHolder(this);
        FirebaseApp.initializeApp(this);
        initRealmConfiguration();

        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

//    public static DataManager getDataManager() {
//        if (sDataManager == null) {
//            sDataManager = new DataManager();
//            sDataManager.init(getContext());
//        }
//        return sDataManager;
//    }

    private void initRealmConfiguration() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("reverserealm")
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static AuthTokenHolder getAuthTokenHolder(){
        return authTokenHolder;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
