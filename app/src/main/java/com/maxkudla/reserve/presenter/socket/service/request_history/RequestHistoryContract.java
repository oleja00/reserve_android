package com.maxkudla.reserve.presenter.socket.service.request_history;


import com.maxkudla.reserve.models.service.ReserveService;

import java.util.List;

public interface RequestHistoryContract {

    interface View {
        void updateRequests(List<ReserveService> requests);

    }

    interface EventDelegate {
        void onGuestClick(String mId, long lId);
    }


}
