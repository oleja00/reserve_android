package com.maxkudla.reserve.presenter.common.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.models.options.Item;
import com.maxkudla.reserve.utils.HanziToPinyin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemAdapter extends RecyclerView.Adapter implements SectionedRecyclerAdapter.SectionedRecyclerDelegate {
    public static final String TAG = "IndexableRecyclerViewAdapter";
    public static final int TYPE_BANNER = 0;
    private final LayoutInflater mLayoutInflater;

    private List<Item> parametrs = null;
    private List<Item> parametrsSending;
    private int mLineNumber = 0;
    LinkedHashMap<String, List<Item>> mSectionedHashMap;

    public ItemAdapter(Context context, List<Item> list) {
        parametrs = list;
        mLayoutInflater = LayoutInflater.from(context);
        parametrsSending = new ArrayList<>();
        init();
    }

    private void init() {
        mSectionedHashMap = new LinkedHashMap<>();
        Collections.sort(parametrs, (item, t1) -> item.getTitle().compareTo(t1.getTitle()));
        mSections.clear();
        for (int i = 0; i < parametrs.size(); i++) {
            String ch = HanziToPinyin.getFirstPinYinChar(parametrs.get(i).getTitle());
            if (ch == null || ch.isEmpty() || !Character.isUpperCase(ch.codePointAt(0)))
                ch = "#";
            List<Item> itemModels = mSectionedHashMap.get(ch);
            if (itemModels == null) {
                itemModels = new ArrayList<>();
            }
            itemModels.add(parametrs.get(i));
            mSectionedHashMap.put(ch, itemModels);
        }
        calculateSectionPosition();
    }

    private void calculateSectionPosition() {
        Set<String> keySet = mSectionedHashMap.keySet();
        String strings[] = new String[keySet.size()];
        keySet.toArray(strings);
        Arrays.sort(strings);
        int pos = 0;
        for (String title : strings) {
            SectionedRecyclerAdapter.Section section = new SectionedRecyclerAdapter.Section(pos, title);
            mSections.add(section);
            pos += mSectionedHashMap.get(title).size();
        }

        mLineNumber = pos;
    }

    @Override
    public List<SectionedRecyclerAdapter.Section> getSections() {
        return mSections;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BannerViewHolder(mLayoutInflater.inflate(R.layout.item_param, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((BannerViewHolder) holder).tvContactName.setText(parametrs.get(position).getTitle());
        ((BannerViewHolder) holder).checkBox.setChecked(parametrs.get(position).isSelected());

        ((BannerViewHolder) holder).relativeLayout.setOnClickListener(view -> {
            parametrs.get(position).setSelected(!((BannerViewHolder)holder).checkBox.isChecked());
            ((BannerViewHolder) holder).checkBox.setChecked(parametrs.get(position).isSelected());
            if(parametrsSending.contains(parametrs.get(position))){
                parametrsSending.remove(parametrs.get(position));
            }else {
                parametrsSending.add(parametrs.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLineNumber;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_BANNER;
    }

    public static class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvContactName)
        TextView tvContactName;
        @BindView(R.id.contactBackgound)
        RelativeLayout relativeLayout;
        @BindView(R.id.checkbox)
        CheckBox checkBox;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            checkBox.setEnabled(false);
        }
    }



}

