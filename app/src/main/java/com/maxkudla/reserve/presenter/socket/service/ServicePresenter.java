package com.maxkudla.reserve.presenter.socket.service;

import com.maxkudla.reserve.domain.socket_service.SetOnlineServiceInteractor;
import com.maxkudla.reserve.models.OnLineModel;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.socket.SocketRouter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ServicePresenter extends BasePresenter<ServiceContract.View, SocketRouter> {

    SetOnlineServiceInteractor mSetOnlineServiceInteractor;
    @Inject
    public ServicePresenter(SetOnlineServiceInteractor setOnlineServiceInteractor) {
        mSetOnlineServiceInteractor = setOnlineServiceInteractor;
    }

    public void setOnline(OnLineModel online) {
        manageDisposable(mSetOnlineServiceInteractor.execute(online)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() ->{
                    getView().showOnline();
                }));
    }
}
