package com.maxkudla.reserve.domain.socket_service;

import com.maxkudla.reserve.domain.baseinteractor.BaseObservableInteractor;
import com.maxkudla.reserve.models.service.ReserveService;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetDataFromSocketServiceInteractor extends BaseObservableInteractor<ReserveService, Void> {

    private SocketServiceRepository socketServiceRepository;

    @Inject
    GetDataFromSocketServiceInteractor(SocketServiceRepository socketServiceRepository) {
        this.socketServiceRepository = socketServiceRepository;
    }


    @Override
    protected Observable<ReserveService> getObservable(Void aVoid) {
        return socketServiceRepository.getDataFromSocket();
    }
}
