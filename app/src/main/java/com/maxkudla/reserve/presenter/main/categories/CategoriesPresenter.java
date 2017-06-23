package com.maxkudla.reserve.presenter.main.categories;

import com.maxkudla.reserve.domain.main.GetCategoriesInteractor;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.main.MainRouter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Developer on 29.04.2017.
 */

public class CategoriesPresenter extends BasePresenter<CategoriesContract.View, MainRouter> implements CategoriesContract.EventDelegate, CategoriesClickListener {

    private GetCategoriesInteractor mGetCategoriesInteractor;

    @Inject
     CategoriesPresenter(GetCategoriesInteractor getCategoriesInteractor) {
        mGetCategoriesInteractor = getCategoriesInteractor;
    }

     void getCategories(){
        manageDisposable(mGetCategoriesInteractor.execute()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categoriesResponse -> getView().initRecycler(categoriesResponse.getData())
                        ,Throwable::printStackTrace));
    }

    @Override
    public void onCategoryClick(String category) {
        getRouter().showOptionsFragment(category);
    }

}
