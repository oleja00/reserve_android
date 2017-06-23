package com.maxkudla.reserve.domain.socket_service;

import com.maxkudla.reserve.domain.baseinteractor.BaseCompletableInteractor;

import javax.inject.Inject;

import io.reactivex.Completable;

public class SendClosedServiceInteractor extends BaseCompletableInteractor<String> {

    private SocketServiceRepository socketServiceRepository;

    @Inject
    public SendClosedServiceInteractor(SocketServiceRepository socketClientRepository) {
        this.socketServiceRepository = socketClientRepository;
    }

    @Override
    protected Completable getCompletable(String queryId) {
        return socketServiceRepository.sendCancel(queryId);
    }
}
