package com.maxkudla.reserve.presenter.socket;

import com.maxkudla.reserve.models.client.ReserveClient;
import com.maxkudla.reserve.models.service.ReserveService;
import com.maxkudla.reserve.presenter.socket.cancel_dialog.CancelOkListener;
import com.maxkudla.reserve.presenter.socket.service.socket_service.common.SocketServiceAdapter;
import com.maxkudla.reserve.presenter.socket.socket_client.common.SocketClientAdapter;

public interface SocketRouter {

    void showBookFragment(ReserveClient reserveClientClientRequest);
    void showBookFragment(ReserveClient reserveClientClientRequest, SocketClientAdapter.OnRequestSentToServer onRequestSentToServer);
    void showImageGallery(String id);
    void showGuestFragment(ReserveService reserveServiceClientRequest, SocketServiceAdapter.OnRequestSentToClient onRequestSentToClient);
//    void showHistory();

    void showCancelDialog(CancelOkListener cancelOkListener);
    void cancelRequest();

    void closeSocket();
}
