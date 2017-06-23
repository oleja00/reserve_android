package com.maxkudla.reserve.domain.baseinteractor;

import io.reactivex.Single;

/**
 * Created by Oleja on 10.05.2017.
 */

public abstract class BaseSingleInteractor<ReturnType, ReceiveType> {

    public Single<ReturnType> execute( ReceiveType params) {
       return getSingle(params);
    }

    public Single<ReturnType> execute() {
        return getSingle(null);
    }

    protected abstract Single<ReturnType> getSingle (ReceiveType type);


}
