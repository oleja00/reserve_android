package com.maxkudla.reserve.data.socket_client;

/**
 * Created by Oleja on 14.05.2017.
 */

public interface SocketClientListener {
    void socketResponse(String json);

    void openSocket();
}
