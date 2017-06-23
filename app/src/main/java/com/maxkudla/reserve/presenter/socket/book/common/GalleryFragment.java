package com.maxkudla.reserve.presenter.socket.book.common;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.presenter.base.BaseFragment;
import com.maxkudla.reserve.presenter.base.BasePresenter;

public class GalleryFragment extends BaseFragment {

    RecyclerView mRecyclerGallery;
    public GalleryFragment() {
    }

    GalleryPresenter galleryPresenter;

    public static GalleryFragment newInstance(String id) {
        return new GalleryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        galleryPresenter = new GalleryPresenter();
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

    }

    @Override
    protected BasePresenter getPresenter() {
        return galleryPresenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        mRecyclerGallery = (RecyclerView) view.findViewById(R.id.recycler_gallery);
        mRecyclerGallery.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecyclerGallery.setAdapter(new ImageGallaryAdapter());
        return view;
    }

}
