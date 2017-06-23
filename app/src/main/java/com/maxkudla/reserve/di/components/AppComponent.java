package com.maxkudla.reserve.di.components;


import android.content.res.Resources;

import com.maxkudla.reserve.di.modules.AppModule;
import com.maxkudla.reserve.di.modules.DataModule;
import com.maxkudla.reserve.di.modules.DomainModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Component(modules = {AppModule.class, DomainModule.class, DataModule.class})
@Singleton
public interface AppComponent {

    Retrofit provideRetrofit();

    Resources provideResources();
}
