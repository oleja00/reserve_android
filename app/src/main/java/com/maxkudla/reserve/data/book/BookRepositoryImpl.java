package com.maxkudla.reserve.data.book;


import com.maxkudla.reserve.data.api.RetrofitManager;
import com.maxkudla.reserve.di.activity.ActivityScope;
import com.maxkudla.reserve.domain.book.BookRepository;
import com.maxkudla.reserve.models.book.BookData;

import javax.inject.Inject;

import io.reactivex.Single;

@ActivityScope
public class BookRepositoryImpl implements BookRepository {

    private RetrofitManager mRetrofitManager;

    @Inject
    public BookRepositoryImpl(RetrofitManager retrofitManager) {
        mRetrofitManager = retrofitManager;
    }

    @Override
    public Single<BookData> getServiceById(String id) {
        return mRetrofitManager.getServiceById(id);
    }
}
