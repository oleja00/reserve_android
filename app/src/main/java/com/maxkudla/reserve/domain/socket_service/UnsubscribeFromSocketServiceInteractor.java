package com.maxkudla.reserve.domain.socket_service;

import com.maxkudla.reserve.domain.baseinteractor.BaseCompletableInteractor;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by Oleja on 15.05.2017.
 */

public class UnsubscribeFromSocketServiceInteractor extends BaseCompletableInteractor<Void> {

    private SocketServiceRepository socketServiceRepository;

    @Inject
    public UnsubscribeFromSocketServiceInteractor(SocketServiceRepository socketServiceRepository) {
        this.socketServiceRepository = socketServiceRepository;
    }

    @Override
    protected Completable getCompletable(Void v) {
        return socketServiceRepository.unsibscribeFromSocket();
    }
}
