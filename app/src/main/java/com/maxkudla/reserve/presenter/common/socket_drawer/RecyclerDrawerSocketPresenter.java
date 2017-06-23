package com.maxkudla.reserve.presenter.common.socket_drawer;

import com.maxkudla.reserve.BuildConfig;
import com.maxkudla.reserve.domain.menu.GetAllMenuItemsInteractor;
import com.maxkudla.reserve.domain.menu.GetSelectedMenuItemInteractor;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.socket.SocketRouter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RecyclerDrawerSocketPresenter extends BasePresenter<RecyclerDrawerSocketContract.View, SocketRouter> {

    private GetAllMenuItemsInteractor mGetAllMenuItemsInteractor;
    private GetSelectedMenuItemInteractor mGetSelectedMenuItemInteractor;
    private RecyclerDrawerSocketContract.EventDelegate mEventDelegate;

    @Inject
    RecyclerDrawerSocketPresenter(GetAllMenuItemsInteractor getAllMenuItemsInteractor
            , GetSelectedMenuItemInteractor getSelectedMenuItemInteractor
    , RecyclerDrawerSocketContract.EventDelegate eventDelegate) {
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
                    //uncomment if we need not open fragment
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
