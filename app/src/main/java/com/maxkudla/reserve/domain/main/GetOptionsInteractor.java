package com.maxkudla.reserve.domain.main;

import com.maxkudla.reserve.domain.baseinteractor.BaseSingleInteractor;
import com.maxkudla.reserve.models.options.Datum;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetOptionsInteractor extends BaseSingleInteractor<List<Datum>,String> {

    private MainRepository mMainRepository;

    @Inject
    GetOptionsInteractor(MainRepository mainRepository) {
        mMainRepository = mainRepository;
    }

    @Override
    protected Single<List<Datum>> getSingle(String category) {
        return mMainRepository.getOptions(category);
    }
}
