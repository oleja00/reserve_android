package com.maxkudla.reserve.domain.socket_client;

import com.maxkudla.reserve.domain.baseinteractor.BaseCompletableInteractor;
import com.maxkudla.reserve.models.service.offer.SendOfferModel;

import javax.inject.Inject;

import io.reactivex.Completable;

public class SendOfferFromClientInteractor extends BaseCompletableInteractor<SendOfferModel>{

    private SocketClientRepository socketClientRepository;

    @Inject
    public SendOfferFromClientInteractor(SocketClientRepository socketClientRepository) {
        this.socketClientRepository = socketClientRepository;
    }

    @Override
    protected Completable getCompletable(SendOfferModel sendOfferModel) {
        return socketClientRepository.sendOfferFromClient(sendOfferModel);
    }

}
