package com.maxkudla.reserve.di.modules;

import android.content.Context;
import android.content.res.Resources;

import com.maxkudla.reserve.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final App mApplication;

    public AppModule(App application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return this.mApplication.getResources();
    }
}
