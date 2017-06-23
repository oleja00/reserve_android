package com.maxkudla.reserve.domain.baseinteractor;

import io.reactivex.Observable;

/**
 * Created by Oleja on 10.05.2017.
 */

public abstract class BaseObservableInteractor<ReturnType, ReceiveType> {

    public Observable<ReturnType> execute(ReceiveType params) {
       return getObservable(params);
    }

    public Observable<ReturnType> execute() {
        return getObservable(null);
    }

    protected abstract Observable<ReturnType> getObservable (ReceiveType type);


}
