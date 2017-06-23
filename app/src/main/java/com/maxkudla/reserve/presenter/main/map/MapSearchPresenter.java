package com.maxkudla.reserve.presenter.main.map;

import com.maxkudla.reserve.domain.main.SendSearchParamInteractor;
import com.maxkudla.reserve.models.options.Location;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.main.MainRouter;

import javax.inject.Inject;

/**
 * Created by Developer on 02.05.2017.
 */

public class MapSearchPresenter extends BasePresenter<MapSearchContract.View, MainRouter> {
    private MapSearchContract.EventDelegate mEventDelegate;

    private SendSearchParamInteractor mSendSearchParamInteractor;

    @Inject
    public MapSearchPresenter(SendSearchParamInteractor sendSearchParamInteractor, MapSearchContract.EventDelegate eventDelegate) {
        mSendSearchParamInteractor = sendSearchParamInteractor;
        mEventDelegate = eventDelegate;
    }


    public void confirmLocation(Double lat, Double lng) {
        Location coordinates = new Location();
        coordinates.setLat(lat);
        coordinates.setLng(lng);
        mEventDelegate.confirmLocation(coordinates);

    }


}
