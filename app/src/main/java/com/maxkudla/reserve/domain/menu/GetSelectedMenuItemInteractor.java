package com.maxkudla.reserve.domain.menu;

import com.maxkudla.reserve.domain.baseinteractor.BaseObservableInteractor;
import com.maxkudla.reserve.models.menu.MenuItem;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetSelectedMenuItemInteractor extends BaseObservableInteractor<MenuItem, Integer> {

    private MenuDrawerRepository mMenuDrawerRepository;

    @Inject
    GetSelectedMenuItemInteractor(MenuDrawerRepository menuDrawerRepository) {
        mMenuDrawerRepository = menuDrawerRepository;
    }

    @Override
    protected Observable<MenuItem> getObservable(Integer integer) {
        return mMenuDrawerRepository.getMenuItemSelected(integer);
    }
}
