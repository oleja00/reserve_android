package com.maxkudla.reserve.presenter.main.map;

import com.maxkudla.reserve.models.options.Location;

/**
 * Created by Developer on 02.05.2017.
 */

public interface MapSearchContract {

    interface View {

    }

    interface EventDelegate {
        void confirmLocation(Location cordinates);
    }
}
