package com.maxkudla.reserve.presenter.main.options;

import com.maxkudla.reserve.domain.main.GetOptionsInteractor;
import com.maxkudla.reserve.models.options.Option;
import com.maxkudla.reserve.models.options.OptionsMapper;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.main.MainRouter;
import com.maxkudla.reserve.presenter.main.options.common.OptionsAdapter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Developer on 30.04.2017.
 *
 */

class OptionsPresenter extends BasePresenter<OptionsContract.View, MainRouter> {

    private GetOptionsInteractor mGetOptionsInteractor;
    private OptionsContract.EventDelegate mEventDelegate;
    @Inject
    OptionsPresenter(GetOptionsInteractor getOptionsInteractor
    ,OptionsContract.EventDelegate eventDelegate) {
        mGetOptionsInteractor = getOptionsInteractor;
        mEventDelegate = eventDelegate;
    }

    void getOptions(String category){
        manageDisposable(mGetOptionsInteractor.execute(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(datums -> {
                            getView().updateView(OptionsMapper.map(datums));
                    mEventDelegate.setDatumsToSend(datums, getView().getCategory());
                        }
                        , Throwable::printStackTrace)
        );
    }

    void openListOptionFragment(Option option, OptionsAdapter.OnOptionsChangeListenerListener listener){
        getRouter().showOptionListFragment(option, listener);
    }

    void openMapScreen() {
        mEventDelegate.setTextToSend(getView().getAbout());
        getRouter().showMapFragment();
    }
}
