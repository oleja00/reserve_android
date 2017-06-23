package com.maxkudla.reserve.domain.socket_service;

import com.maxkudla.reserve.domain.baseinteractor.BaseCompletableInteractor;
import com.maxkudla.reserve.models.service.offer.SendOfferModel;

import javax.inject.Inject;

import io.reactivex.Completable;

public class SendOfferFromServiceInteractor extends BaseCompletableInteractor<SendOfferModel>{

    private SocketServiceRepository mSocketServiceRepository;

    @Inject
    public SendOfferFromServiceInteractor(SocketServiceRepository socketServiceRepository) {
        mSocketServiceRepository = socketServiceRepository;
    }

    @Override
    protected Completable getCompletable(SendOfferModel sendOfferModel) {
        return mSocketServiceRepository.sendOfferFromService(sendOfferModel);
    }
}
