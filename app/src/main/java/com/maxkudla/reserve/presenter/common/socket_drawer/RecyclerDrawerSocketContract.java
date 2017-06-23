package com.maxkudla.reserve.presenter.common.socket_drawer;

import com.maxkudla.reserve.models.menu.MenuItem;

import java.util.List;

public interface RecyclerDrawerSocketContract {

    interface View {
        void updateAdapter(List<MenuItem> items);
        void updateSelectedPositionAdapter(MenuItem item);
    }

    interface EventDelegate {
        void openFragment(int position);
    }

}
