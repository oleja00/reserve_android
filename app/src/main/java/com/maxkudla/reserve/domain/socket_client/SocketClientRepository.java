package com.maxkudla.reserve.domain.socket_client;

import com.maxkudla.reserve.models.client.ClientListRequestModel;
import com.maxkudla.reserve.models.client.ReserveClient;
import com.maxkudla.reserve.models.service.offer.SendOfferModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface SocketClientRepository {
    Observable<ReserveClient> getDataFromSocket();
    Completable unsibscribeFromSocket();
    Completable sendCancel(String queryId);
    Single<ClientListRequestModel> getClientHistoryAllExistingRequests(String status);
    Maybe<List<Object>> getHistory(String status);
    Completable subscribeToSocket();
//    Completable sendOfferFromService(SendOfferModel sendOfferModel);
    Completable sendOfferFromClient(SendOfferModel sendOfferModel);
}
