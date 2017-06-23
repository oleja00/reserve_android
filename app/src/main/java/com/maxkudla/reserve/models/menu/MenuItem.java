package com.maxkudla.reserve.models.menu;

import android.support.annotation.DrawableRes;

public class MenuItem {

    public static final int REQUEST = 0;
    public static final int HISTORY = 1;
    public static final int SHARE = 2;
    public static final int SETTINGS = 3;
    public static final int CHAT = 4;
    public static final int SIGNOUT = 5;


    private int position;
    private String menuName;
    private boolean canBeChoosen;
    @DrawableRes private int img;

    public boolean isCanBeChoosen() {
        return canBeChoosen;
    }

    public void setCanBeChoosen(boolean canBeChoosen) {
        this.canBeChoosen = canBeChoosen;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @DrawableRes
    public int getImg() {
        return img;
    }

    public void setImg(@DrawableRes int img) {
        this.img = img;
    }
}
