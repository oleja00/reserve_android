package com.maxkudla.reserve.presenter.common.drawer;

import com.maxkudla.reserve.models.menu.MenuItem;

import java.util.List;

public interface RecyclerDrawerContract {

    interface View {
        void updateAdapter(List<MenuItem> items);
        void updateSelectedPositionAdapter(MenuItem item);
    }

    interface EventDelegate {
        void openFragment(int position);
    }

}
