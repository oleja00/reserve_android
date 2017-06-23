package com.maxkudla.reserve.data.socket_service;

/**
 * Created by Oleja on 14.05.2017.
 */

public interface SocketServiceListener {
    void socketResponse(String json);

    void openSocket();

}
