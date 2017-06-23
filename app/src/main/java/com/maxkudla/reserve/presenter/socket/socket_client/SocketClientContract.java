package com.maxkudla.reserve.presenter.socket.socket_client;


import com.maxkudla.reserve.models.client.ReserveClient;

import java.util.List;

public interface SocketClientContract {

    interface View {
        void addToRecyclerItems(List<ReserveClient> reserveClientClientRequests);

        void addToRecycerAnItem(ReserveClient reserveClientClientRequest);
    }

    interface EventDelegate {
        void showBookFragment(int pos);
    }

}
