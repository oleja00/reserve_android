package com.maxkudla.reserve.domain.socket_client;

import com.maxkudla.reserve.domain.baseinteractor.BaseObservableInteractor;
import com.maxkudla.reserve.models.client.ReserveClient;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetDataFromSocketClientInteractor extends BaseObservableInteractor<ReserveClient, Void> {

    private SocketClientRepository socketClientRepository;

    @Inject
    GetDataFromSocketClientInteractor(SocketClientRepository socketClientRepository) {
        this.socketClientRepository = socketClientRepository;
    }


    @Override
    protected Observable<ReserveClient> getObservable(Void aVoid) {
        return socketClientRepository.getDataFromSocket();
    }
}
