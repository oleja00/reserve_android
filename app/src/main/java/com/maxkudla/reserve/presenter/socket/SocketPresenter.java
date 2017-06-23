package com.maxkudla.reserve.presenter.socket;

import com.maxkudla.reserve.di.activity.ActivityScope;
import com.maxkudla.reserve.models.menu.MenuItem;
import com.maxkudla.reserve.presenter.common.socket_drawer.RecyclerDrawerSocketContract;
import com.maxkudla.reserve.presenter.socket.service.request_history.RequestHistoryContract;
import com.maxkudla.reserve.presenter.socket.service.socket_service.SocketServiceContract;
import com.maxkudla.reserve.presenter.socket.socket_client.SocketClientContract;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.maxkudla.reserve.Constants.USER_CLIENT;
import static com.maxkudla.reserve.Constants.USER_SERVICE;

@ActivityScope
public class SocketPresenter implements
        SocketServiceContract.EventDelegate
        , SocketClientContract.EventDelegate
        , RecyclerDrawerSocketContract.EventDelegate
        , RequestHistoryContract.EventDelegate {

    private SocketContract.View mView;
    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public SocketPresenter() {
    }

    @Override
    public void showBookFragment(int pos) {

    }

    @Override
    public void openFragment(int position) {
        mView.closeDrawer();
        switch (position) {
            case MenuItem.REQUEST:

                break;
            case MenuItem.HISTORY:
                mView.historyActivity();
                break;
            case MenuItem.SETTINGS:
                mView.settingsActivity();
                break;
            case MenuItem.SIGNOUT:
                mView.logout();
                break;
        }
    }

    @Override
    public void showGuestFragment(int pos) {

    }

    @Override
    public void onGuestClick(String mId, long lId) {
        mView.showGuest(mId, lId);
    }

    public void manageDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    public void deleteDisposable(Disposable disposable) {
        disposables.remove(disposable);
    }

    void attachView(SocketContract.View view) {
        this.mView = view;
    }

    void onDetachView() {
        if (disposables != null) {
            disposables.dispose();
            disposables = null;
        }
    }

    public void showScocketFragment(int typeUser, String status, String serviceId) {
        switch (typeUser) {
            case USER_CLIENT:
                if(status == null){
                    mView.showClientFragment();
                }else {
                    mView.showClientFragment(status);
                }

                break;
            case USER_SERVICE:
                mView.showServiceFragment(serviceId);
                break;

        }
    }


}
