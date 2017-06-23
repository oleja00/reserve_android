package com.maxkudla.reserve.presenter.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Developer on 19.04.2017.
 */

public abstract class BasePresenter<View, Router> {
    private View mView;
    private Router router;
    private CompositeDisposable disposables = new CompositeDisposable();

    public Router getRouter() {
        return router;
    }

    public void setRouter(Router router) {
        this.router = router;
    }

    public View getView() {
        return mView;
    }

    public void setView(View view) {
        this.mView = view;
    }

    public void manageDisposable(Disposable disposable){
        disposables.add(disposable);
    }

    public void deleteDisposable(Disposable disposable){
        disposables.remove(disposable);
    }

    protected void onStopView() {
        if (disposables != null){
            disposables.dispose();
        }
    }
    protected void onDetachView() {
        if (disposables != null){
            disposables.dispose();
            disposables = null;
        }
    }

}

