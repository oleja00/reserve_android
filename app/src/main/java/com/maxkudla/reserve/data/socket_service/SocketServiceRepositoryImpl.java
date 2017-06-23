package com.maxkudla.reserve.data.socket_service;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maxkudla.reserve.App;
import com.maxkudla.reserve.BuildConfig;
import com.maxkudla.reserve.data.api.RetrofitManager;
import com.maxkudla.reserve.di.activity.ActivityScope;
import com.maxkudla.reserve.domain.socket_service.SocketServiceRepository;
import com.maxkudla.reserve.models.OnLineModel;
import com.maxkudla.reserve.models.service.ReserveRequest;
import com.maxkudla.reserve.models.service.ReserveService;
import com.maxkudla.reserve.models.service.ServiceListRequestModel;
import com.maxkudla.reserve.models.service.offer.SendOfferModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

/**
 * Created by Oleja on 15.05.2017.
 */
@ActivityScope
public class SocketServiceRepositoryImpl implements SocketServiceRepository, SocketServiceListener {
    private PublishSubject<ReserveService> subject = PublishSubject.create();

    private OkHttpClient client;
    private WebSocket ws;

    private RetrofitManager mRetrofitManager;

    @Inject
    SocketServiceRepositoryImpl(RetrofitManager retrofitManager) {
        mRetrofitManager = retrofitManager;
    }


    @Override
    public Single<ServiceListRequestModel> getAllExistingRequests(String status) {
        return mRetrofitManager.getAllExistingService(status);
    }

    @Override
    public Maybe<List<Object>> getHistory(String status) {
        return mRetrofitManager.getHistory(status);
    }

    @Override
    public Observable<ReserveService> getDataFromSocket() {
        client = new OkHttpClient();
        Request request = new Request.Builder().url("http://207.154.254.225:3000?access_key=" + App.getAuthTokenHolder().getToken()).build();
        EchoWebSocketServiceListener listener = new EchoWebSocketServiceListener(this);
        ws = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
        return Observable.fromCallable(ReserveService::new)
                .mergeWith(subject);
    }

    @Override
    public Completable subscribeToSocket() {
        return null;
    }

    @Override
    public Completable sendOfferFromService(SendOfferModel sendOfferModel) {
        return mRetrofitManager.sendInfoToSocket(sendOfferModel);
    }

    @Override
    public Completable sendOfferFromClient(SendOfferModel sendOfferModel) {
        return mRetrofitManager.sendInfoToSocket(sendOfferModel);
    }

    @Override
    public Completable unsibscribeFromSocket() {
        return Completable.fromAction(() -> {
            ws.close(1000, "close");
        });
    }

    @Override
    public void socketResponse(String json) {
        ReserveRequest r = null;
        try {
            Log.d("key - ", json);
            System.out.println("Receiving socketResponse : " + json);

            Gson gson = new GsonBuilder().create();
            r = gson.fromJson(json, ReserveRequest.class);

            System.out.println("Receiving rrrsrsrsr : " + r.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (r != null) {
                sendData(r.getData());
            }
        }
    }

    @Override
    public Completable sendCancel(String queryId) {
        return mRetrofitManager.sendClosed(queryId);
    }

    @Override
    public Completable setOnline(OnLineModel onLineModel) {
        return mRetrofitManager.setOnline(onLineModel);
    }

    @Override
    public void openSocket() {
        mRetrofitManager.getAllExistingService("request_by_client,reserved_by_client")
                .map(ServiceListRequestModel::getData)
                .flatMapObservable(Observable::fromIterable)
                .doOnNext(this::sendData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reserve -> {
                }, throwable -> {
                    if (BuildConfig.DEBUG) {
                        throwable.printStackTrace();
                    }
                });
    }

    private void sendData(ReserveService json) {
        subject.onNext(json);
    }


}
