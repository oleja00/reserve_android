package com.maxkudla.reserve.presenter.socket.service.socket_service;

import com.maxkudla.reserve.models.service.ReserveService;

import java.util.List;

public interface SocketServiceContract {

    interface View {
        void addToRecyclerItems(List<ReserveService> reserveServiceRequests);

        void addToRecycerAnItem(ReserveService reserveServiceRequest);
    }

    interface EventDelegate {
        void showGuestFragment(int pos);
    }

}
