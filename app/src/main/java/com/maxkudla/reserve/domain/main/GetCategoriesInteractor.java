package com.maxkudla.reserve.domain.main;

import com.maxkudla.reserve.domain.baseinteractor.BaseObservableInteractor;
import com.maxkudla.reserve.models.categories.CategoriesResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetCategoriesInteractor extends BaseObservableInteractor<CategoriesResponse, Void>{

    private MainRepository mMainRepository;

    @Inject
    public GetCategoriesInteractor(MainRepository mainRepository) {
        mMainRepository = mainRepository;
    }

//    public Observable<CategoriesResponse> getCategoriesRemote(){
//        return mMainRepository.getCategoriesRemote();
//    }

    @Override
    protected Observable<CategoriesResponse> getObservable(Void aVoid) {
        return mMainRepository.getCategoriesRemote();
    }
}
