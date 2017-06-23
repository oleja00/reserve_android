package com.maxkudla.reserve.presenter.main.categories;

import com.maxkudla.reserve.models.categories.Category;

import java.util.List;

/**
 * Created by Developer on 29.04.2017.
 */

public interface CategoriesContract {
    interface View {

        void initRecycler(List<Category> data);
    }

    interface EventDelegate {

    }
}
