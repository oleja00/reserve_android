package com.maxkudla.reserve.presenter.main.parametrs;

import com.maxkudla.reserve.domain.main.GetParamInteractor;
import com.maxkudla.reserve.models.parametrs.Parametr;
import com.maxkudla.reserve.models.parametrs.ParametrWrapper;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.main.MainRouter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Developer on 29.04.2017.
 */

public class ParametrsPresenter extends BasePresenter<ParametrsContract.View, MainRouter> implements ParametrsContract.EventDelegate, ParametrClickListener {

    private GetParamInteractor mGetParamInteractor;

    @Inject
    ParametrsPresenter(GetParamInteractor getParamInteractor) {
        mGetParamInteractor = getParamInteractor;
    }

    @Override
    public void onParametrClick() {

    }

    void getParams() {
        manageDisposable(mGetParamInteractor.getParamRemote()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(parametrsResponse -> {
                    List<ParametrWrapper> parametrData = new ArrayList<>();
                    for (Parametr parametr :
                            parametrsResponse.getData()) {
                        parametrData.add(new ParametrWrapper(parametr, false));
                    }

                    getView().initRecycler(parametrData);
                }, Throwable::printStackTrace));
    }

}
