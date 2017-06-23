package com.maxkudla.reserve.presenter.main;

import com.maxkudla.reserve.models.options.Option;
import com.maxkudla.reserve.presenter.main.options.common.OptionsAdapter;

/**
 * Created by Developer on 29.04.2017.
 *
 */

public interface MainRouter {
    void showCategoriesFragment();
    void showOptionsFragment(String category);
    void showMapFragment();
    void showImageGallery(String id);
    void showOptionListFragment(Option optionList, OptionsAdapter.OnOptionsChangeListenerListener listener);
}
