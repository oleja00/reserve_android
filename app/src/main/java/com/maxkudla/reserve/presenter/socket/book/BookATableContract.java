package com.maxkudla.reserve.presenter.socket.book;


interface BookATableContract {

    interface View {
        void offerHasBeenSent();
        void anErrorOccurred();
    }

    interface EventDelegate {

    }
}
