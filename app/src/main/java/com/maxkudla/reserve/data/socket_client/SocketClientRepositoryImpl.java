package com.maxkudla.reserve.data.socket_client;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maxkudla.reserve.App;
import com.maxkudla.reserve.BuildConfig;
import com.maxkudla.reserve.data.api.RetrofitManager;
import com.maxkudla.reserve.di.activity.ActivityScope;
import com.maxkudla.reserve.domain.socket_client.SocketClientRepository;
import com.maxkudla.reserve.models.client.ClientListRequestModel;
import com.maxkudla.reserve.models.client.ReserveClient;
import com.maxkudla.reserve.models.client.ReserveClientRequest;
import com.maxkudla.reserve.models.service.offer.SendOfferModel;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

@ActivityScope
public class SocketClientRepositoryImpl implements SocketClientRepository, SocketClientListener {
    private Subject<ReserveClient> subject = PublishSubject.create();


    private OkHttpClient client;
    private WebSocket ws;

    private RetrofitManager mRetrofitManager;

    @Inject
    SocketClientRepositoryImpl(RetrofitManager retrofitManager) {
        mRetrofitManager = retrofitManager;
    }

    @Override
    public Single<ClientListRequestModel>  getClientHistoryAllExistingRequests(String status) {
        return mRetrofitManager.getAllExistingClient(status);
    }

    @Override
    public Maybe<List<Object>> getHistory(String status) {
        return mRetrofitManager.getHistory(status);
    }

    @Override
    public Observable<ReserveClient> getDataFromSocket() {
        client = new OkHttpClient();
        Request request = new Request.Builder().url("http://207.154.254.225:3000?access_key=" + App.getAuthTokenHolder().getToken()).build();
        EchoWebSocketClientListener listener = new EchoWebSocketClientListener(this);
        ws = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
        return subject;
    }

    @Override
    public Completable subscribeToSocket() {
        return null;
    }

//    @Override
//    public Completable sendOfferFromService(SendOfferModel sendOfferModel) {
//        return mRetrofitManager.sendInfoToSocket(sendOfferModel);
//    }

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
        ReserveClientRequest r = null;
        try {
            Log.d("key - ", json);
            System.out.println("Receiving socketResponse : " + json);

            Gson gson = new GsonBuilder().create();
            r = gson.fromJson(json, ReserveClientRequest.class);

            System.out.println("Receiving rrrsrsrsr : " + r.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (r != null) {
                sendData(r.getData());
            }
        }
    }

    @Override
    public void openSocket() {
//        mRetrofitManager.getAllExistingClient("offer_by_service")
//                .map(ClientListRequestModel::getData)
//                .flatMapObservable(Observable::fromIterable)
//                .doOnNext(this::sendData)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(reserve ->{}, throwable -> {
//                    if (BuildConfig.DEBUG){
//                        throwable.printStackTrace();
//                    }
//                });

        mRetrofitManager.getAllExistingClient("offer_by_service,reserved_by_client,reserved_by_service")
                .map(ClientListRequestModel::getData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reserveClients -> {
                    if(reserveClients!=null){
                        Collections.reverse(reserveClients);
                        for (ReserveClient reserv : reserveClients) {
                            sendData(reserv);
                        }
                    }

                },throwable -> {
                    if (BuildConfig.DEBUG){
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public Completable sendCancel(String queryId){
        return mRetrofitManager.sendClosed(queryId);
    }

    private void sendData(ReserveClient json) {
        subject.onNext(json);
    }


}
