package com.maxkudla.reserve.domain.socket_client;

import com.maxkudla.reserve.domain.baseinteractor.BaseCompletableInteractor;

import javax.inject.Inject;

import io.reactivex.Completable;

public class SendClosedClientInteractor extends BaseCompletableInteractor<String> {

    private SocketClientRepository socketClientRepository;

    @Inject
    public SendClosedClientInteractor(SocketClientRepository socketClientRepository) {
        this.socketClientRepository = socketClientRepository;
    }

    @Override
    protected Completable getCompletable(String queryId) {
        return socketClientRepository.sendCancel(queryId);
    }
}
