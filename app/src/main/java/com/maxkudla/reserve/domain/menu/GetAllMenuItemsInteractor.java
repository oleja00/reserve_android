package com.maxkudla.reserve.domain.menu;

import com.maxkudla.reserve.domain.baseinteractor.BaseObservableInteractor;
import com.maxkudla.reserve.models.menu.MenuItem;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetAllMenuItemsInteractor extends BaseObservableInteractor<List<MenuItem>, Void>{

    private MenuDrawerRepository mMenuDrawerRepository;

    @Inject
    GetAllMenuItemsInteractor(MenuDrawerRepository menuDrawerRepository) {
        mMenuDrawerRepository = menuDrawerRepository;
    }

    @Override
    protected Observable<List<MenuItem>> getObservable(Void v) {
        return mMenuDrawerRepository.getMenuItems();
    }
}
