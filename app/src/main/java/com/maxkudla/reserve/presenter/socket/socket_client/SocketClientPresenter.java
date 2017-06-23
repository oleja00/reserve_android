package com.maxkudla.reserve.presenter.socket.socket_client;

import com.maxkudla.reserve.domain.socket_client.GetDataFromSocketClientInteractor;
import com.maxkudla.reserve.domain.socket_client.SendClosedClientInteractor;
import com.maxkudla.reserve.domain.socket_client.UnsubscribeFromSocketClientInteractor;
import com.maxkudla.reserve.models.client.ReserveClient;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.socket.SocketRouter;
import com.maxkudla.reserve.presenter.socket.cancel_dialog.CancelOkListener;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SocketClientPresenter extends BasePresenter<SocketClientContract.View, SocketRouter> {

//    private SocketServiceContract.EventDelegate mEventDelegate;

    private GetDataFromSocketClientInteractor getDataFromSocketClientInteractor;
    private UnsubscribeFromSocketClientInteractor unsubscribeFromSocketClientInteractor;
    private SendClosedClientInteractor mSendClosedClientInteractor;

    private Disposable disposable;

    @Inject
    SocketClientPresenter(GetDataFromSocketClientInteractor getDataFromSocketClientInteractor
            , UnsubscribeFromSocketClientInteractor unsubscribeFromSocketClientInteractor
            , SendClosedClientInteractor sendClosedClientInteractor) {
        this.getDataFromSocketClientInteractor = getDataFromSocketClientInteractor;
        this.unsubscribeFromSocketClientInteractor = unsubscribeFromSocketClientInteractor;
        mSendClosedClientInteractor = sendClosedClientInteractor;
    }

    void openBookFragment(ReserveClient reserveClientClientRequest) {
        getRouter().showBookFragment(reserveClientClientRequest);
    }

    void getAllExistingRequests(String status) {
//        manageDisposable(
//                // TODO: put here restouran info and change interactor
//                mGetAllExistingRequestsInteractor.execute(status)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(reserveRequests -> {
//                            getView().addToRecyclerItems(reserveRequests);
//                        }, Throwable::printStackTrace)
//        );
    }

    void getDataFromSocket() {
        disposable = getDataFromSocketClientInteractor.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reserveRequest -> {

//                    System.out.println("Receiving rrrrrr " + reserveRequest.toString());

                    if (reserveRequest.getStatus() != null) {
                        getView().addToRecycerAnItem(reserveRequest);
                    }

                }, throwable -> {
//                    System.out.println("Receiving error " + throwable.getLocalizedMessage());
                });


    }

    void sendClosed(String queryId){
        manageDisposable(  mSendClosedClientInteractor.execute(queryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    getRouter().cancelRequest();
                }, Throwable::printStackTrace));
    }

    void unsubscribeFromSocket() {
        disposable.dispose();
        unsubscribeFromSocketClientInteractor.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {

                }, Throwable::printStackTrace);
    }


     void showCancelDialog(CancelOkListener cancelOkListener) {
        getRouter().showCancelDialog(cancelOkListener);
    }

    public void closeSocket() {
        getRouter().closeSocket();
    }

//    @Override
//    protected void onStopView() {
//        super.onStopView();
//    }
}
