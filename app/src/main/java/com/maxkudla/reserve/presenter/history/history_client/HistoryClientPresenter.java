package com.maxkudla.reserve.presenter.history.history_client;

import com.maxkudla.reserve.domain.socket_client.GetAllExistingRequestsHistoryClientInteractor;
import com.maxkudla.reserve.models.client.ReserveClient;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.socket.SocketRouter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HistoryClientPresenter extends BasePresenter<HistoryClientContract.View, SocketRouter> {
    private GetAllExistingRequestsHistoryClientInteractor mGetAllExistingRequestsInteractor;
    private HistoryClientContract.EventDelegate mEventDelegate;
    @Inject
    public HistoryClientPresenter(GetAllExistingRequestsHistoryClientInteractor getAllExistingRequestsInteractor,
                                  HistoryClientContract.EventDelegate eventDelegate) {
        mGetAllExistingRequestsInteractor = getAllExistingRequestsInteractor;
        mEventDelegate = eventDelegate;
    }

    void openClientFragment(ReserveClient client){
        mEventDelegate.openClientFragment(client);
    }

    void getAllExistingRequests(String status) {
        manageDisposable(
                // TODO: put here restouran info and change interactor
                mGetAllExistingRequestsInteractor.execute(status)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(reserveRequests -> {
                            getView().updateUi(reserveRequests);
                        }, Throwable::printStackTrace)
        );
    }

}
