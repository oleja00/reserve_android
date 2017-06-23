package com.maxkudla.reserve.domain.socket_service;

import com.maxkudla.reserve.models.OnLineModel;
import com.maxkudla.reserve.models.service.ReserveService;
import com.maxkudla.reserve.models.service.ServiceListRequestModel;
import com.maxkudla.reserve.models.service.offer.SendOfferModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Oleja on 15.05.2017.
 *
 */

public interface SocketServiceRepository {
    Observable<ReserveService> getDataFromSocket();
    Completable unsibscribeFromSocket();
    Single<ServiceListRequestModel> getAllExistingRequests(String status);
    Maybe<List<Object>> getHistory(String status);
    Completable subscribeToSocket();
    Completable sendOfferFromService(SendOfferModel sendOfferModel);
    Completable sendOfferFromClient(SendOfferModel sendOfferModel);
    Completable sendCancel(String queryId);
    Completable setOnline(OnLineModel onLineModel);
}
