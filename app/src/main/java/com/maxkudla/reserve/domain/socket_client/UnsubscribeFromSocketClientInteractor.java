package com.maxkudla.reserve.domain.socket_client;

import com.maxkudla.reserve.domain.baseinteractor.BaseCompletableInteractor;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by Oleja on 15.05.2017.
 */

public class UnsubscribeFromSocketClientInteractor extends BaseCompletableInteractor<Void> {

    private SocketClientRepository socketClientRepository;

    @Inject
    public UnsubscribeFromSocketClientInteractor(SocketClientRepository socketClientRepository) {
        this.socketClientRepository = socketClientRepository;
    }

    @Override
    protected Completable getCompletable(Void v) {
        return socketClientRepository.unsibscribeFromSocket();
    }
}
