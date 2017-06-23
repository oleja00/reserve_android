package com.maxkudla.reserve.domain.menu;

import com.maxkudla.reserve.models.menu.MenuItem;

import java.util.List;

import io.reactivex.Observable;

public interface MenuDrawerRepository {

    Observable<List<MenuItem>> getMenuItems();

    Observable<MenuItem> getMenuItemSelected(int position);

}
