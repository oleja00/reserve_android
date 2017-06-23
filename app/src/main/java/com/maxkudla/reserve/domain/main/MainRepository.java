package com.maxkudla.reserve.domain.main;

import com.maxkudla.reserve.models.categories.CategoriesResponse;
import com.maxkudla.reserve.models.options.Datum;
import com.maxkudla.reserve.models.options.RequestOptions;
import com.maxkudla.reserve.models.parametrs.ParametrsResponse;
import com.maxkudla.reserve.models.search.SearchResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface MainRepository {

    Observable<CategoriesResponse> getCategoriesRemote();

    Observable<ParametrsResponse> getParamRemote();

    Single<SearchResponse> sendSearchParam(RequestOptions requestOptions);

    Single<List<Datum>> getOptions(String category);
}
