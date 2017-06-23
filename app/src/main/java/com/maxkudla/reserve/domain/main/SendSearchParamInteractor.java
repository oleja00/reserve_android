package com.maxkudla.reserve.domain.main;

import com.maxkudla.reserve.models.options.RequestOptions;
import com.maxkudla.reserve.models.search.SearchResponse;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by Developer on 07.05.2017.
 */

public class SendSearchParamInteractor {
    MainRepository mMainRepository;


    @Inject
    public SendSearchParamInteractor(MainRepository mMainRepository) {
        this.mMainRepository = mMainRepository;
    }

    public Single<SearchResponse> sendSearchParam(RequestOptions requestOptions){
        return mMainRepository.sendSearchParam(requestOptions);
    }
}
