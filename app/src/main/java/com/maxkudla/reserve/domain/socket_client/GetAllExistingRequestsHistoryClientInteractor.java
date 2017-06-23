package com.maxkudla.reserve.domain.socket_client;

import com.maxkudla.reserve.domain.baseinteractor.BaseSingleInteractor;
import com.maxkudla.reserve.models.client.ClientListRequestModel;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetAllExistingRequestsHistoryClientInteractor extends BaseSingleInteractor<ClientListRequestModel, String> {

    private SocketClientRepository socketClientRepository;

    @Inject
    public GetAllExistingRequestsHistoryClientInteractor(SocketClientRepository socketClientRepository) {
        this.socketClientRepository = socketClientRepository;
    }

    @Override
    protected Single<ClientListRequestModel> getSingle(String status) {
        return socketClientRepository.getClientHistoryAllExistingRequests(status);
    }
}
