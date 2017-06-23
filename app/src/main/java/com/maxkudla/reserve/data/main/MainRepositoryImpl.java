package com.maxkudla.reserve.data.main;

import com.maxkudla.reserve.data.api.RetrofitManager;
import com.maxkudla.reserve.di.activity.ActivityScope;
import com.maxkudla.reserve.domain.main.MainRepository;
import com.maxkudla.reserve.models.categories.CategoriesResponse;
import com.maxkudla.reserve.models.options.Datum;
import com.maxkudla.reserve.models.options.RequestOptions;
import com.maxkudla.reserve.models.parametrs.ParametrsResponse;
import com.maxkudla.reserve.models.search.SearchResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

@ActivityScope
public class MainRepositoryImpl implements MainRepository {

    private RetrofitManager mRetrofitManager;

    @Inject
    public MainRepositoryImpl(RetrofitManager retrofitManager) {
        mRetrofitManager = retrofitManager;
    }

    @Override
    public Observable<CategoriesResponse> getCategoriesRemote() {
        return mRetrofitManager.getCategoriesRemote();
    }

    @Override
    public Observable<ParametrsResponse> getParamRemote() {
        return mRetrofitManager.getParamRemote();
    }

    @Override
    public Single<SearchResponse> sendSearchParam(RequestOptions requestOptions) {
        return mRetrofitManager.sendSearchParam(requestOptions);
    }

    @Override
    public Single<List<Datum>> getOptions(String category) {
        return mRetrofitManager.getOptions(category);
    }
}
