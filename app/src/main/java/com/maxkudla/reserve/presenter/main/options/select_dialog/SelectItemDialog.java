package com.maxkudla.reserve.presenter.main.options.select_dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.models.options.Item;

import java.util.List;

import biz.kasual.materialnumberpicker.MaterialNumberPicker;

/**
 * Created by Oleja on 20.05.2017.
 */

public class SelectItemDialog extends DialogFragment {
    private MaterialNumberPicker mPicker;
    private int mPosition;
    private SelectedListener mSelectedListener;

    private List<Item> itemList;

    public static SelectItemDialog newInstance() {
        return new SelectItemDialog();
    }

    public void setUpPicker(List<Item> items, int position, SelectedListener selectedListener) {
        itemList = items;
        mPosition = position;
        mSelectedListener = selectedListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_select, null);
        mPicker = (MaterialNumberPicker) v.findViewById(R.id.picker);
        mPicker.setMinValue(0);
        mPicker.setMaxValue(itemList.size() - 1);
        for (int i = 0; i < itemList.size() ; i++) {
            if(itemList.get(i).isSelected()){
                mPicker.setValue(i);
                break;
            }
        }
        mPicker.setFormatter(value -> itemList.get(value).getTitle());
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setUpButtons(v);
        return v;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, 0);
    }

    public void setUpButtons(View view) {
        Button bCancel = (Button) view.findViewById(R.id.bCancel);
        bCancel.setOnClickListener(v -> dismiss());
        Button bOk = (Button) view.findViewById(R.id.bOk);
        bOk.setOnClickListener(v -> {
            for (int i = 0; i < itemList.size(); i++) {
                itemList.get(i).setSelected(i == mPicker.getValue());
            }
            mSelectedListener.selectItem(mPosition);
            dismiss();
        });

    }

}



