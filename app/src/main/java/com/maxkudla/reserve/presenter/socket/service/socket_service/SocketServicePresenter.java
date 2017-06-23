package com.maxkudla.reserve.presenter.socket.service.socket_service;

import com.maxkudla.reserve.domain.socket_service.GetAllExistingRequestsServiceInteractor;
import com.maxkudla.reserve.domain.socket_service.GetDataFromSocketServiceInteractor;
import com.maxkudla.reserve.domain.socket_service.UnsubscribeFromSocketServiceInteractor;
import com.maxkudla.reserve.models.service.ReserveService;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.socket.SocketRouter;
import com.maxkudla.reserve.presenter.socket.service.socket_service.common.SocketServiceAdapter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SocketServicePresenter extends BasePresenter<SocketServiceContract.View, SocketRouter> {

//    private SocketServiceContract.EventDelegate mEventDelegate;

    private GetDataFromSocketServiceInteractor getDataFromSocketServiceInteractor;
    private UnsubscribeFromSocketServiceInteractor unsubscribeFromSocketServiceInteractor;
    private GetAllExistingRequestsServiceInteractor mGetAllExistingRequestsServiceInteractor;
    private Disposable disposable;

    @Inject
    SocketServicePresenter(GetDataFromSocketServiceInteractor getDataFromSocketServiceInteractor
            , UnsubscribeFromSocketServiceInteractor unsubscribeFromSocketServiceInteractor
            , GetAllExistingRequestsServiceInteractor getAllExistingRequestsServiceInteractor) {
        this.getDataFromSocketServiceInteractor = getDataFromSocketServiceInteractor;
        this.unsubscribeFromSocketServiceInteractor = unsubscribeFromSocketServiceInteractor;
        mGetAllExistingRequestsServiceInteractor = getAllExistingRequestsServiceInteractor;
    }

    void openBookFragment(ReserveService reserveServiceRequest, SocketServiceAdapter.OnRequestSentToClient onRequestSentToClient) {
        getRouter().showGuestFragment(reserveServiceRequest, onRequestSentToClient);
    }

    void getAllExistingRequests(String status) {
//        manageDisposable(
//                mGetAllExistingRequestsServiceInteractor.execute(status)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(reserveRequests -> {
//                            getView().addToRecyclerItems(reserveRequests);
//                        }, Throwable::printStackTrace)
//        );
    }

    void getDataFromSocket() {
        disposable = getDataFromSocketServiceInteractor.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reserveRequest -> {
                    if (reserveRequest.getStatus() != null) {
                        getView().addToRecycerAnItem(reserveRequest);
                    }
                }, Throwable::printStackTrace);


    }

    void unsubscribeFromSocket() {
        disposable.dispose();
        unsubscribeFromSocketServiceInteractor.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                }, Throwable::printStackTrace);
    }


}
