package com.maxkudla.reserve.di.modules;

import com.maxkudla.reserve.data.book.BookRepositoryImpl;
import com.maxkudla.reserve.data.login.LoginRepositoryImpl;
import com.maxkudla.reserve.data.main.MainRepositoryImpl;
import com.maxkudla.reserve.data.menu.MenuDrawerRepositoryImpl;
import com.maxkudla.reserve.data.socket_client.SocketClientRepositoryImpl;
import com.maxkudla.reserve.data.socket_service.SocketServiceRepositoryImpl;
import com.maxkudla.reserve.data.splash.SplashRepositoryImpl;
import com.maxkudla.reserve.di.activity.ActivityScope;
import com.maxkudla.reserve.domain.book.BookRepository;
import com.maxkudla.reserve.domain.login.LoginRepository;
import com.maxkudla.reserve.domain.main.MainRepository;
import com.maxkudla.reserve.domain.menu.MenuDrawerRepository;
import com.maxkudla.reserve.domain.socket_client.SocketClientRepository;
import com.maxkudla.reserve.domain.socket_service.SocketServiceRepository;
import com.maxkudla.reserve.domain.splash.SplashRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DomainModule {

    @Binds
    @ActivityScope
    abstract LoginRepository provideLoginRepository(LoginRepositoryImpl loginRepository);

    @Binds
    @ActivityScope
    abstract SplashRepository provideSplashRepository(SplashRepositoryImpl splashRepository);

    @Binds
    @ActivityScope
    abstract BookRepository provideBookRepository(BookRepositoryImpl bookRepository);

    @Binds
    @ActivityScope
    abstract MainRepository provideMainRepository(MainRepositoryImpl mainRepository);

    @Binds
    @ActivityScope
    abstract SocketServiceRepository provideSocketServiceRepository(SocketServiceRepositoryImpl socketRepository);

    @Binds
    @ActivityScope
    abstract SocketClientRepository provideSocketClientRepository(SocketClientRepositoryImpl socketRepository);

    @Binds
    @ActivityScope
    abstract MenuDrawerRepository provideMenuDrawerRepository(MenuDrawerRepositoryImpl menuDrawerRepository);

}
