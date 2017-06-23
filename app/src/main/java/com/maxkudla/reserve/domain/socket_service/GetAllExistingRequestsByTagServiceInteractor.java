package com.maxkudla.reserve.domain.socket_service;

import com.maxkudla.reserve.domain.baseinteractor.BaseSingleInteractor;
import com.maxkudla.reserve.models.service.ServiceListRequestModel;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetAllExistingRequestsByTagServiceInteractor extends BaseSingleInteractor<ServiceListRequestModel, String> {

    private SocketServiceRepository mSocketServiceRepository;

    @Inject
    public GetAllExistingRequestsByTagServiceInteractor(SocketServiceRepository socketServiceRepository) {
        mSocketServiceRepository = socketServiceRepository;
    }

    @Override
    protected Single<ServiceListRequestModel> getSingle(String status) {
        return mSocketServiceRepository.getAllExistingRequests(status);
    }
}
