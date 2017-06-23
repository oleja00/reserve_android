package com.maxkudla.reserve.presenter.socket.book.common;


import com.maxkudla.reserve.models.categories.Category;

import java.util.List;

interface GalleryContract {

    interface View {

        void initRecycler(List<Category> data);
    }

    interface EventDelegate {

    }
}
