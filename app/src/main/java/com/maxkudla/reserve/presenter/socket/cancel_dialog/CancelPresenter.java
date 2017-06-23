package com.maxkudla.reserve.presenter.socket.cancel_dialog;

import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.socket.SocketRouter;

import javax.inject.Inject;

/**
 * Created by Oleja on 06.06.2017.
 */

public class CancelPresenter extends BasePresenter<CancelContract.View, SocketRouter> {

    @Inject
    public CancelPresenter() {

    }

    public void onCancelAccept(CancelOkListener cancelOkListener) {
        cancelOkListener.onCancelClickCallback();
    }
}
