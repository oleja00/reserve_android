package com.maxkudla.reserve.presenter.main.parametrs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.di.common.Injector;
import com.maxkudla.reserve.di.components.ActivityComponent;
import com.maxkudla.reserve.models.options.Option;
import com.maxkudla.reserve.models.parametrs.ParametrWrapper;
import com.maxkudla.reserve.presenter.base.BaseFragment;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.common.adapters.ItemAdapter;
import com.maxkudla.reserve.presenter.common.views.IndexableRecyclerViewForOptionItem;
import com.maxkudla.reserve.presenter.main.MainActivity;
import com.maxkudla.reserve.presenter.main.options.common.OptionsAdapter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Developer on 29.04.2017.
 */

public class ParametrsFragment extends BaseFragment implements ParametrsContract.View {
    private static final String ID = "ID";
    private String paramId;
    private Option option;
    private OptionsAdapter.OnOptionsChangeListenerListener mOnOptionsChangeListenerListener;
    @Inject
    ParametrsPresenter mParametrsPresenter;
    @BindView(R.id.param_recycler)
    IndexableRecyclerViewForOptionItem recyclerView;
//    @BindView(R.id.fab)
//    FloatingActionButton fab;

    private List<ParametrWrapper> parametrWrappers;

    public static ParametrsFragment newInstance() {
        ParametrsFragment fragment = new ParametrsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_param, container, false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.getComponent(getActivity(), ActivityComponent.class).inject(this);
        if (getArguments() != null) {

        }
        mParametrsPresenter.setView(this);
        mParametrsPresenter.setRouter((MainActivity) getActivity());
    }

    @Override
    protected BasePresenter getPresenter() {
        return mParametrsPresenter;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public boolean toolbarNavigationActive() {
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        Flowable.timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    recyclerView.getmLetterBar().invalidate();
                }, Throwable::printStackTrace);

    }

    @Override
    protected void initView() {
//        mParametrsPresenter.getParams();
        ItemAdapter parametrAdapter = new ItemAdapter(getActivity(), option.getItems());
        recyclerView.setAdapter(parametrAdapter);

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mParametrsPresenter.getRouter().showMapFragment();
//            }
//        });

    }


    @Override
    public void initRecycler(List<ParametrWrapper> data) {
//        ParametrAdapter parametrAdapter = new ParametrAdapter(getActivity(), data);
//        ItemAdapter parametrAdapter = new ItemAdapter(getActivity(), option.getItems());
//        recyclerView.setAdapter(parametrAdapter);
//        recyclerView.getmLetterBar().invalidate();

    }

    public void setOption(Option option) {
        this.option = option;
    }

    public void setOnOptionsItemChangeListener(OptionsAdapter.OnOptionsChangeListenerListener onOptionsChangeListenerListener) {
        this.mOnOptionsChangeListenerListener = onOptionsChangeListenerListener;
    }

    @Override
    public void onDestroy() {
        mOnOptionsChangeListenerListener.onOptionItemChangeClick();
        super.onDestroy();
    }
}
