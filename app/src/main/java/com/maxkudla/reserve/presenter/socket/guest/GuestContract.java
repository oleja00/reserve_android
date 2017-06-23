package com.maxkudla.reserve.presenter.socket.guest;

public interface GuestContract {

    interface View {
        void offerHasBeenSent();
        void anErrorOccurred();
    }

    interface EventDelegate {
    }

}
