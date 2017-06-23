package com.maxkudla.reserve.presenter.common.drawer;

import com.maxkudla.reserve.BuildConfig;
import com.maxkudla.reserve.domain.menu.GetAllMenuItemsInteractor;
import com.maxkudla.reserve.domain.menu.GetSelectedMenuItemInteractor;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.main.MainRouter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RecyclerDrawerPresenter extends BasePresenter<RecyclerDrawerContract.View, MainRouter> {

    private GetAllMenuItemsInteractor mGetAllMenuItemsInteractor;
    private GetSelectedMenuItemInteractor mGetSelectedMenuItemInteractor;
    private RecyclerDrawerContract.EventDelegate mEventDelegate;

    @Inject
    RecyclerDrawerPresenter(GetAllMenuItemsInteractor getAllMenuItemsInteractor
            , GetSelectedMenuItemInteractor getSelectedMenuItemInteractor
    ,RecyclerDrawerContract.EventDelegate eventDelegate) {
        mGetAllMenuItemsInteractor = getAllMenuItemsInteractor;
        mGetSelectedMenuItemInteractor = getSelectedMenuItemInteractor;
        mEventDelegate = eventDelegate;
    }

    void getItemsMenu() {
        manageDisposable(mGetAllMenuItemsInteractor.execute()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(itemList -> getView().updateAdapter(itemList), throwable -> {
                    if (BuildConfig.DEBUG) {
                        throwable.printStackTrace();
                    }
                }));
    }

    void setSelectedMenuItem(int position) {
        manageDisposable(mGetSelectedMenuItemInteractor.execute(position)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(menuItem -> {
//                    getView().updateSelectedPositionAdapter(menuItem);
                    mEventDelegate.openFragment(menuItem.getPosition());
                        }, throwable -> {
                            if (BuildConfig.DEBUG) {
                                throwable.printStackTrace();
                            }
                        }
                ));

    }

    void dettachView(){
        onDetachView();
    }

}
