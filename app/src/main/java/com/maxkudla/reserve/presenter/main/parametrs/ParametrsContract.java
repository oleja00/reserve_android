package com.maxkudla.reserve.presenter.main.parametrs;

import com.maxkudla.reserve.models.parametrs.ParametrWrapper;

import java.util.List;

/**
 * Created by Developer on 29.04.2017.
 */

public interface ParametrsContract {
    interface View {
        void initRecycler(List<ParametrWrapper> data);
    }

    interface EventDelegate {

    }
}
