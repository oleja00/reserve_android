package com.maxkudla.reserve.domain.main;

import com.maxkudla.reserve.models.parametrs.ParametrsResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetParamInteractor {

    private MainRepository mMainRepository;

    @Inject
    public GetParamInteractor(MainRepository mainRepository) {
        mMainRepository = mainRepository;
    }

    public Observable<ParametrsResponse> getParamRemote() {
        return mMainRepository.getParamRemote();
    }

}
