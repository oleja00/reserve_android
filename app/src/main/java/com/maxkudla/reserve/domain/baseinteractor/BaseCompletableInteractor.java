package com.maxkudla.reserve.domain.baseinteractor;

import io.reactivex.Completable;

/**
 * Created by Oleja on 10.05.2017.
 */

public abstract class BaseCompletableInteractor< ReceiveType> {

    public Completable execute(ReceiveType params) {
       return getCompletable(params);
    }

    public Completable execute() {
        return getCompletable(null);
    }

    protected abstract Completable getCompletable (ReceiveType type);


}
