package com.maxkudla.reserve.data.socket_client;

import android.util.Log;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by Oleja on 14.05.2017.
 *
 */

public final class EchoWebSocketClientListener extends WebSocketListener {
    private static final int NORMAL_CLOSURE_STATUS = 1000;

    private SocketClientListener mScocketListener;

    public EchoWebSocketClientListener(SocketClientListener socketServiceListener) {
        mScocketListener = socketServiceListener;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
//        System.out.println("Receiving onMessage text : " + response.toString());
        Log.i("Receiving : ",  response.toString());
        mScocketListener.openSocket();
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
//        System.out.println("Receiving onMessage text : " + text);
        Log.i("Receiving : ", text);
            mScocketListener.socketResponse(text);

    }
    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
//        System.out.println("Receiving onMessage bytes : " + bytes.toString());
        Log.i("Receiving bytes : ", bytes.toString());
//        try {
//            mScocketListener.socketResponse(
//                    new JSONObject(bytes.toString().substring(bytes.toString().indexOf("=")+1, bytes.toString().length()-1)));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }
    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(NORMAL_CLOSURE_STATUS, null);
//        System.out.println("Receiving onClosing : " + reason);
        Log.i("Closing : ", code + " / " + reason);
    }
    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
//        System.out.println("Receiving onFailure : " + response.toString());
//        Log.i("Error : ", t.getMessage());
    }
}
