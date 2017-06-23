package com.maxkudla.reserve.di.modules;

import com.maxkudla.reserve.di.activity.ActivityScope;
import com.maxkudla.reserve.presenter.common.drawer.RecyclerDrawerContract;
import com.maxkudla.reserve.presenter.common.socket_drawer.RecyclerDrawerSocketContract;
import com.maxkudla.reserve.presenter.history.HistoryContract;
import com.maxkudla.reserve.presenter.history.HistoryPresenter;
import com.maxkudla.reserve.presenter.history.history_client.HistoryClientContract;
import com.maxkudla.reserve.presenter.history.history_service.HistoryServiceContract;
import com.maxkudla.reserve.presenter.main.MainPresenter;
import com.maxkudla.reserve.presenter.main.map.MapSearchContract;
import com.maxkudla.reserve.presenter.main.options.OptionsContract;
import com.maxkudla.reserve.presenter.socket.SocketPresenter;
import com.maxkudla.reserve.presenter.socket.service.request_history.RequestHistoryContract;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class NavigationModule {

    @Binds
    @ActivityScope
    abstract OptionsContract.EventDelegate provideOptionEventDelegate(MainPresenter mainPresenter);

    @Binds
    @ActivityScope
    abstract MapSearchContract.EventDelegate provideMapEventDelegate(MainPresenter mainPresenter);

    @Binds
    @ActivityScope
    abstract RecyclerDrawerContract.EventDelegate provideRecyclerDrawerDelegate(MainPresenter mainPresenter);

    @Binds
    @ActivityScope
    abstract RecyclerDrawerSocketContract.EventDelegate provideRecyclerDrawerSocketDelegate(SocketPresenter socketPresenter);

    @Binds
    @ActivityScope
    abstract RequestHistoryContract.EventDelegate provideRequestHistoryDelegate(SocketPresenter socketPresenter);

    @Binds
    @ActivityScope
    abstract HistoryContract.EventDelegate provideHistoryDelegate(HistoryPresenter historyPresenter);

    @Binds
    @ActivityScope
    abstract HistoryClientContract.EventDelegate provideHistoryClientDelegate(HistoryPresenter historyPresenter);

    @Binds
    @ActivityScope
    abstract HistoryServiceContract.EventDelegate provideHistoryServiceDelegate(HistoryPresenter historyPresenter);

}
