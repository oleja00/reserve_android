package com.maxkudla.reserve.presenter.socket.guest;

import com.maxkudla.reserve.BuildConfig;
import com.maxkudla.reserve.domain.socket_service.SendClosedServiceInteractor;
import com.maxkudla.reserve.domain.socket_service.SendOfferFromServiceInteractor;
import com.maxkudla.reserve.models.service.offer.SendOfferModel;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.socket.SocketRouter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GuestPresenter extends BasePresenter<GuestContract.View, SocketRouter> {

    private SendOfferFromServiceInteractor mSendOfferFromServiceInteractor;
    SendClosedServiceInteractor mSendClosedServiceInteractor;

    @Inject
    public GuestPresenter(SendOfferFromServiceInteractor sendOfferFromServiceInteractor
            , SendClosedServiceInteractor sendClosedServiceInteractor) {
        mSendOfferFromServiceInteractor = sendOfferFromServiceInteractor;
        mSendClosedServiceInteractor = sendClosedServiceInteractor;
    }

    void sendOffer(SendOfferModel sendOfferModel) {
        manageDisposable(mSendOfferFromServiceInteractor.execute(sendOfferModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    getView().offerHasBeenSent();
                }, throwable -> {
                    getView().anErrorOccurred();
                    if (BuildConfig.DEBUG) {
                        throwable.printStackTrace();
                    }
                }));
    }

    void sendClosed(SendOfferModel model) {
        manageDisposable(mSendOfferFromServiceInteractor.execute(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                }, Throwable::printStackTrace));
    }

}
