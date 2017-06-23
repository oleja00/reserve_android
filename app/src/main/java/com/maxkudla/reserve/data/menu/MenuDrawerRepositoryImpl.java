package com.maxkudla.reserve.data.menu;

import android.content.res.Resources;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.domain.menu.MenuDrawerRepository;
import com.maxkudla.reserve.models.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MenuDrawerRepositoryImpl implements MenuDrawerRepository {

    private List<MenuItem> menuItems;
    private Resources mResources;
    @Inject
    MenuDrawerRepositoryImpl(Resources resources) {
        mResources = resources;
        menuItems = fillMenuItems();
    }

    @Override
    public Observable<List<MenuItem>> getMenuItems() {
        return Observable.just(menuItems);
    }

    @Override
    public Observable<MenuItem> getMenuItemSelected(int position) {
        return Observable.fromIterable(menuItems)
                .filter(menuItem -> menuItem.getPosition() == position);
    }

    private List<MenuItem> fillMenuItems() {
        List<MenuItem> menus = new ArrayList<>();

        MenuItem menuReservation = new MenuItem();
        menuReservation.setCanBeChoosen(false);
        menuReservation.setImg(R.drawable.ic_wifi_tethering_black_24dp);
        menuReservation.setPosition(MenuItem.REQUEST);
        menuReservation.setMenuName(mResources.getString(R.string.menu_item_request));
        menus.add(menuReservation);

        MenuItem menuHistory = new MenuItem();
        menuHistory.setCanBeChoosen(true);
        menuHistory.setImg(R.drawable.ic_archive_black_24dp);
        menuHistory.setPosition(MenuItem.HISTORY);
        menuHistory.setMenuName(mResources.getString(R.string.menu_item_history));
        menus.add(menuHistory);

        MenuItem menuShare = new MenuItem();
        menuShare.setCanBeChoosen(true);
        menuShare.setImg(R.drawable.ic_share_black_24dp);
        menuShare.setPosition(MenuItem.SHARE);
        menuShare.setMenuName(mResources.getString(R.string.share));
        menus.add(menuShare);

        MenuItem menuSettings = new MenuItem();
        menuSettings.setCanBeChoosen(true);
        menuSettings.setImg(R.drawable.ic_settings_black_24dp);
        menuSettings.setPosition(MenuItem.SETTINGS);
        menuSettings.setMenuName(mResources.getString(R.string.menu_item_settings));
        menus.add(menuSettings);

        MenuItem menuChat = new MenuItem();
        menuChat.setCanBeChoosen(true);
        menuChat.setImg(R.drawable.ic_chat_bubble_outline_black_24dp);
        menuChat.setPosition(MenuItem.CHAT);
        menuChat.setMenuName(mResources.getString(R.string.contact_us));
        menus.add(menuChat);

        MenuItem menuSingOut = new MenuItem();
        menuSingOut.setCanBeChoosen(true);
        menuSingOut.setImg(R.drawable.ic_directions_run_black_24dp);
        menuSingOut.setPosition(MenuItem.SIGNOUT);
        menuSingOut.setMenuName(mResources.getString(R.string.menu_item_sing_out));
        menus.add(menuSingOut);

        return menus;

    }
}
