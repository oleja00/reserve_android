package com.maxkudla.reserve.presenter.history.history_client;


import com.maxkudla.reserve.models.client.ClientListRequestModel;
import com.maxkudla.reserve.models.client.ReserveClient;

public interface HistoryClientContract {

    interface View {
        void updateUi(ClientListRequestModel reserveRequests);
    }

    interface EventDelegate {
        void openClientFragment(ReserveClient client);
    }


}
