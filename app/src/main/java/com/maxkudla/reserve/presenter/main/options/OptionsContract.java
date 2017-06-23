package com.maxkudla.reserve.presenter.main.options;

import com.maxkudla.reserve.models.options.Datum;
import com.maxkudla.reserve.models.options.ItemOptions;

import java.util.List;

/**
 * Created by Developer on 30.04.2017.
 *
 */

public interface OptionsContract {

    interface View {
        void updateView(List<ItemOptions> data);
        String getCategory();
        String getAbout();
    }

    interface EventDelegate {
        void setDatumsToSend(List<Datum> datums, String category) ;

        void setTextToSend(String about);
    }
}
