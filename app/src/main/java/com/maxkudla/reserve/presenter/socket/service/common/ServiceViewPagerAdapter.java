package com.maxkudla.reserve.presenter.socket.service.common;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.presenter.socket.service.request_history.RequestHistoryFragment;
import com.maxkudla.reserve.presenter.socket.service.socket_service.SocketServiceFragment;

import java.util.ArrayList;
import java.util.List;

public class ServiceViewPagerAdapter extends FragmentPagerAdapter {

    public static final int PAGES = 2;
    private List<String> mTitles;

    public ServiceViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mTitles = new ArrayList<>();
        mTitles.add(context.getString(R.string.orders));
        mTitles.add(context.getString(R.string.guests));
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return SocketServiceFragment.newInstance();
            case 1:
                return RequestHistoryFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
