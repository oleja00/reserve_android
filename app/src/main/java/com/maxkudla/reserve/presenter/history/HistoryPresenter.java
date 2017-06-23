package com.maxkudla.reserve.presenter.history;

import com.maxkudla.reserve.di.activity.ActivityScope;
import com.maxkudla.reserve.domain.splash.SplashInteractor;
import com.maxkudla.reserve.models.client.ReserveClient;
import com.maxkudla.reserve.models.service.ReserveService;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.history.history_client.HistoryClientContract;
import com.maxkudla.reserve.presenter.history.history_service.HistoryServiceContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Oleja on 05.06.2017.
 */

@ActivityScope
public class HistoryPresenter extends BasePresenter<HistoryContract.View, HistoryRouter>
        implements HistoryContract.EventDelegate
        , HistoryServiceContract.EventDelegate
        , HistoryClientContract.EventDelegate {
    private SplashInteractor mSplashInteractor;
    private HistoryContract.View mView;

    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    HistoryPresenter(SplashInteractor splashInteractor) {
        mSplashInteractor = splashInteractor;
    }

    public void attachView(HistoryContract.View view) {
        this.mView = view;
    }

    public void onDetachView() {
        if (disposables != null) {
            disposables.dispose();
            disposables = null;
        }
    }

    public void manageDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    public void deleteDisposable(Disposable disposable) {
        disposables.remove(disposable);
    }

    @Override
    public void openClientFragment(ReserveClient client) {
        mView.clientFragment(client);
    }

    @Override
    public void openServiceFragment(ReserveService service) {
        mView.serviceFragment(service);
    }

    void launchFragments() {

        disposables.add(mSplashInteractor.checkTokenRemote()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tokenResponse -> {
                    mView.launchToFragment(tokenResponse.getData().getType());
                }, Throwable::printStackTrace));

    }
}
