package com.maxkudla.reserve.presenter.socket.book;

import com.maxkudla.reserve.BuildConfig;
import com.maxkudla.reserve.domain.book.GetBookByIdInteractor;
import com.maxkudla.reserve.domain.socket_client.SendClosedClientInteractor;
import com.maxkudla.reserve.domain.socket_client.SendOfferFromClientInteractor;
import com.maxkudla.reserve.models.service.offer.SendOfferModel;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.socket.SocketRouter;
import com.maxkudla.reserve.presenter.socket.cancel_dialog.CancelOkListener;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BookATablePresenter extends BasePresenter<BookATableContract.View, SocketRouter> {

    private GetBookByIdInteractor mGetBookByIdInteractor;
    private SendOfferFromClientInteractor mSendOfferFromClientInteractor;
    private SendClosedClientInteractor mSendClosedClientInteractor;

    @Inject
    public BookATablePresenter(GetBookByIdInteractor getBookByIdInteractor
            , SendOfferFromClientInteractor sendOfferFromClientInteractor
            , SendClosedClientInteractor sendClosedClientInteractor) {
        mGetBookByIdInteractor = getBookByIdInteractor;
        mSendOfferFromClientInteractor = sendOfferFromClientInteractor;
        mSendClosedClientInteractor = sendClosedClientInteractor;
    }


    void openGallery(String id) {
        getRouter().showImageGallery(id);
    }

    void openHistory() {
//        getRouter().showHistory();
    }

    void sendOffer(SendOfferModel sendOfferModel) {
        manageDisposable(mSendOfferFromClientInteractor.execute(sendOfferModel)
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

    void sendCancel(SendOfferModel sendOfferModel) {
        manageDisposable(mSendOfferFromClientInteractor.execute(sendOfferModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    getRouter().closeSocket();
                }, throwable -> {
                    getView().anErrorOccurred();
                    if (BuildConfig.DEBUG) {
                        throwable.printStackTrace();
                    }
                }));

    }
    public void cancelRequest(String query) {
        manageDisposable(  mSendClosedClientInteractor.execute(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {}, Throwable::printStackTrace));
    }

    public void showCancelDialog(CancelOkListener cancleOkListener) {
        getRouter().showCancelDialog(cancleOkListener);
    }


}
