package com.maxkudla.reserve.presenter.main;

import com.maxkudla.reserve.di.activity.ActivityScope;
import com.maxkudla.reserve.domain.main.SendSearchParamInteractor;
import com.maxkudla.reserve.models.menu.MenuItem;
import com.maxkudla.reserve.models.options.Datum;
import com.maxkudla.reserve.models.options.Location;
import com.maxkudla.reserve.models.options.RequestOptions;
import com.maxkudla.reserve.presenter.common.drawer.RecyclerDrawerContract;
import com.maxkudla.reserve.presenter.main.map.MapSearchContract;
import com.maxkudla.reserve.presenter.main.options.OptionsContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@ActivityScope
public class MainPresenter implements
        OptionsContract.EventDelegate,
        MapSearchContract.EventDelegate,
        RecyclerDrawerContract.EventDelegate {

    private MainContract.View mView;
    private CompositeDisposable disposables = new CompositeDisposable();
    private RequestOptions mRequestOptions;
    private SendSearchParamInteractor mSendSearchParamInteractor;

    @Inject
    MainPresenter(SendSearchParamInteractor sendSearchParamInteractor) {
        mRequestOptions = new RequestOptions();
        mSendSearchParamInteractor = sendSearchParamInteractor;
    }

    @Override
    public void setDatumsToSend(List<Datum> datums, String category) {
        mRequestOptions.setDatums(datums);

        mRequestOptions.setCategory(category);
    }

    @Override
    public void setTextToSend(String about) {
        mRequestOptions.setNote(about);
    }

    @Override
    public void confirmLocation(Location cordinates) {
        mRequestOptions.setLocation(cordinates);
        sendRequest();
    }

    public void manageDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    public void deleteDisposable(Disposable disposable) {
        disposables.remove(disposable);
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
            case MenuItem.CHAT:
                mView.intercomChat();
                break;
            case MenuItem.SHARE:
                mView.shareApp();
                break;
        }
    }

    void attachView(MainContract.View view) {
        this.mView = view;
    }

    void onDetachView() {
        if (disposables != null) {
            disposables.dispose();
            disposables = null;
        }
    }

    private void sendRequest() {
        manageDisposable(mSendSearchParamInteractor.sendSearchParam(mRequestOptions)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchResponse -> mView.socketActivity(searchResponse.getData().getQueryId()), Throwable::printStackTrace));

    }

}
