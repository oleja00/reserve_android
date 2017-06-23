package com.maxkudla.reserve.presenter.history.history_service;

import com.maxkudla.reserve.domain.socket_service.GetAllExistingRequestsServiceInteractor;
import com.maxkudla.reserve.models.service.ReserveService;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.socket.SocketRouter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HistoryServicePresenter extends BasePresenter<HistoryServiceContract.View, SocketRouter> {

    private GetAllExistingRequestsServiceInteractor getAllExistingRequestsServiceInteractor;
    private HistoryServiceContract.EventDelegate mEventDelegate;


    @Inject
    public HistoryServicePresenter(GetAllExistingRequestsServiceInteractor getAllExistingRequestsServiceInteractor,
                                   HistoryServiceContract.EventDelegate eventDelegate) {
        this.getAllExistingRequestsServiceInteractor = getAllExistingRequestsServiceInteractor;
        mEventDelegate = eventDelegate;
    }

    void openServiceFragment(ReserveService service){
        mEventDelegate.openServiceFragment(service);
    }

    void getAllExistingRequests(String status) {
        manageDisposable(
                getAllExistingRequestsServiceInteractor.execute(status)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(reserveRequests -> {
                            getView().updateUi(reserveRequests);
                        }, Throwable::printStackTrace)
        );
    }

}
