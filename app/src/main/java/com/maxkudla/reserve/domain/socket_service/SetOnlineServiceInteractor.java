package com.maxkudla.reserve.domain.socket_service;

import com.maxkudla.reserve.domain.baseinteractor.BaseCompletableInteractor;
import com.maxkudla.reserve.models.OnLineModel;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by Oleja on 18.06.2017.
 */

public class SetOnlineServiceInteractor extends BaseCompletableInteractor<OnLineModel> {

    private SocketServiceRepository socketServiceRepository;

    @Inject
    public SetOnlineServiceInteractor(SocketServiceRepository socketClientRepository) {
        this.socketServiceRepository = socketClientRepository;
    }

    @Override
    protected Completable getCompletable(OnLineModel onLineModel) {
        return socketServiceRepository.setOnline(onLineModel);
    }
}
