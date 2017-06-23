package com.maxkudla.reserve.presenter.main.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.di.common.Injector;
import com.maxkudla.reserve.di.components.ActivityComponent;
import com.maxkudla.reserve.models.categories.Category;
import com.maxkudla.reserve.presenter.base.BaseFragment;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.common.adapters.CategoriesAdapter;
import com.maxkudla.reserve.presenter.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Developer on 29.04.2017.
 */

public class CategoriesFragment extends BaseFragment implements CategoriesContract.View{

    @Inject CategoriesPresenter mCategoriesPresenter;

    private List<Category> categoriesList;

    public static CategoriesFragment newInstance() {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.categories_recyler)
    RecyclerView recylerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.getComponent(getActivity(), ActivityComponent.class).inject(this);
        if (getArguments() != null) {

        }
        mCategoriesPresenter.setView(this);
        mCategoriesPresenter.setRouter((MainActivity)getActivity());
    }

    @Override
    protected BasePresenter getPresenter() {
        return mCategoriesPresenter;
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
    protected void initView() {
        mCategoriesPresenter.getCategories();
        categoriesList = new ArrayList<>();
        recylerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recylerView.setAdapter(new CategoriesAdapter(categoriesList, mCategoriesPresenter));
    }


    @Override
    public void initRecycler(List<Category> data) {
        categoriesList.clear();
//        for (int i = 0; i <34 ; i++) {
            categoriesList.addAll(data);
//        }

        recylerView.getAdapter().notifyDataSetChanged();
    }
}
