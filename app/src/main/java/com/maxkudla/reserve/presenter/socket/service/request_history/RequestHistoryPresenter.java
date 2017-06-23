package com.maxkudla.reserve.presenter.socket.service.request_history;

import com.maxkudla.reserve.domain.socket_service.GetAllExistingRequestsByTagServiceInteractor;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.socket.SocketRouter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RequestHistoryPresenter extends BasePresenter<RequestHistoryContract.View, SocketRouter> {

    private RequestHistoryContract.EventDelegate mEventDelegate;
    private GetAllExistingRequestsByTagServiceInteractor mGetAllExistingRequestsByTagServiceInteractor;

    @Inject
    RequestHistoryPresenter(RequestHistoryContract.EventDelegate eventDelegate
            , GetAllExistingRequestsByTagServiceInteractor getAllExistingRequestsByTagServiceInteractor) {
        mEventDelegate = eventDelegate;
        mGetAllExistingRequestsByTagServiceInteractor = getAllExistingRequestsByTagServiceInteractor;
    }

    void onGuestClick(String mId, long lId) {
        mEventDelegate.onGuestClick(mId, lId);
    }


    void sendRequestToUpdate(String status) {
        manageDisposable(mGetAllExistingRequestsByTagServiceInteractor.execute(status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(serviceListRequestModel ->
                    getView().updateRequests(serviceListRequestModel.getData()), Throwable::printStackTrace)
        );

    }
}
