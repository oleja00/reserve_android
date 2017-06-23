package com.maxkudla.reserve.presenter.main.options;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.di.common.Injector;
import com.maxkudla.reserve.di.components.ActivityComponent;
import com.maxkudla.reserve.models.options.ItemOptions;
import com.maxkudla.reserve.models.options.Option;
import com.maxkudla.reserve.presenter.base.BaseFragment;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.main.MainActivity;
import com.maxkudla.reserve.presenter.main.options.common.OptionsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Developer on 30.04.2017.
 */

public class OptionsFragment extends BaseFragment implements OptionsContract.View {

    public static final String CATEGORY_KEY = "CATEGORY_KEY";

    @Inject
    OptionsPresenter mOptionsPresenter;

    @BindView(R.id.recycler_view_options)
    RecyclerView mRecyclerView;
    @BindView(R.id.etComment)
    EditText etComment;
    @BindView(R.id.rToShow)
    RelativeLayout rToShow;
    @BindView(R.id.fake_options)
    View fake_options;

    private String mCategory;

    public static OptionsFragment newInstance(String category) {
        OptionsFragment fragment = new OptionsFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY_KEY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.getComponent(getActivity(), ActivityComponent.class).inject(this);
        mCategory = getArguments().getString(CATEGORY_KEY);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        View view = inflater.inflate(R.layout.fragment_options, container, false);

        mOptionsPresenter.setView(this);
        mOptionsPresenter.setRouter((MainActivity) getActivity());
        mOptionsPresenter.getOptions(mCategory);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Animation anim = new AlphaAnimation(0.5f, 1.0f);
        anim.setDuration(1500); //You can manage the time
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        fake_options.startAnimation(anim);

    }

    @Override
    public void updateView(List<ItemOptions> data) {
        fake_options.setVisibility(View.GONE);
        fake_options.clearAnimation();
        rToShow.setVisibility(View.VISIBLE);
        mRecyclerView.setAdapter(new OptionsAdapter(data, mOnOptionsClickListener, getFragmentManager()));
//        for (int i = 0; i < data.size(); i++) {
//            System.out.println("BLALBA " + data.get(i).toString());
//        }
    }

    @Override
    public String getCategory() {
        return mCategory;
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
    protected BasePresenter getPresenter() {
        return mOptionsPresenter;
    }

    @Override
    protected void initView() {

    }

    @Override
    public String getAbout(){
        return etComment.getText().toString();
    }

    @OnClick(R.id.bStartSearch)
    public void bStartSearchClick() {
        mOptionsPresenter.openMapScreen();
    }

    private OptionsAdapter.OnOptionsClickListener mOnOptionsClickListener = new OptionsAdapter.OnOptionsClickListener() {
        @Override
        public void onOptionListClick(Option option, OptionsAdapter.OnOptionsChangeListenerListener listener) {
            mOptionsPresenter.openListOptionFragment(option, listener);
        }

        @Override
        public void onOptionSwitchClick(Option option) {

        }

        @Override
        public void onOptionSelectClick(Option option) {

        }
    };

}


