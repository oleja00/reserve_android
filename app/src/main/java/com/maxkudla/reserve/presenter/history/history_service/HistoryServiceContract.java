package com.maxkudla.reserve.presenter.history.history_service;


import com.maxkudla.reserve.models.service.ReserveService;
import com.maxkudla.reserve.models.service.ServiceListRequestModel;

public interface HistoryServiceContract {

    interface View {
        void updateUi(ServiceListRequestModel reserveRequests);
    }

    interface EventDelegate {
        void openServiceFragment(ReserveService service);
    }


}
