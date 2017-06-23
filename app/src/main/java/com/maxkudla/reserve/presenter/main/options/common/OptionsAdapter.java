package com.maxkudla.reserve.presenter.main.options.common;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.pickerview.OptionsPickerView;
import com.maxkudla.reserve.R;
import com.maxkudla.reserve.models.options.Datum;
import com.maxkudla.reserve.models.options.Item;
import com.maxkudla.reserve.models.options.ItemOptions;
import com.maxkudla.reserve.models.options.Option;
import com.maxkudla.reserve.models.options.OptionsMapper;
import com.maxkudla.reserve.presenter.main.options.select_dialog.SelectItemDialog;
import com.maxkudla.reserve.presenter.main.options.select_dialog.SelectedListener;

import java.util.ArrayList;
import java.util.List;

public class OptionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements SelectedListener {

    private List<ItemOptions> data;
    private OnOptionsClickListener mOnOptionsClickListener;
    FragmentManager fragmentManager;

    private final static int LABEL = 0;
    private final static int SELECT = 1;
    private final static int LIST = 2;
    private final static int SWITCHER = 3;

    public OptionsAdapter(List<ItemOptions> data,
                          OnOptionsClickListener onOptionsClickListener, FragmentManager fragmentManager) {
        this.data = data;
        mOnOptionsClickListener = onOptionsClickListener;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case LABEL:
                return new LabelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_label, parent, false));
            case SELECT:
                return new SelectViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_options_select, parent, false));
            case LIST:
                return new ListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_option_list, parent, false));
            case SWITCHER:
                return new SwitcherViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_options_switcher, parent, false));

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case LABEL:
                Datum datum = (Datum) data.get(position);
                LabelViewHolder labelViewHolder = (LabelViewHolder) holder;
                labelViewHolder.tvLabelDatum.setText(datum.getLabel());
                break;
            case SELECT:
                Option optionSelect = (Option) data.get(position);
                SelectViewHolder selectViewHolder = (SelectViewHolder) holder;
                selectViewHolder.tvOptionSelect.setText(optionSelect.getTitle());
                SelectItemDialog selectItemDialog = SelectItemDialog.newInstance();

                List<String> listForPicker = new ArrayList<>();
                int defaultSelection = 0;
                for (int i = 0; i < optionSelect.getItems().size() ; i++) {
                    if(optionSelect.getItems().get(i).isSelected()){
                        ((SelectViewHolder) holder).tvDefault.setText(optionSelect.getItems().get(i).getTitle());
                        defaultSelection = i;

                    }
                    listForPicker.add(optionSelect.getItems().get(i).getTitle());
                }

                OptionsPickerView pvOptions = new  OptionsPickerView.Builder(selectViewHolder.itemView.getContext(), new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        Log.d("PickerTag", options1+"");
                        ((SelectViewHolder) holder).tvDefault.setText(optionSelect.getItems().get(options1).getTitle());
                        for(int i = 0; i<optionSelect.getItems().size(); i++){
                            if(i==options1){
                                ((SelectViewHolder) holder).tvDefault.setText(optionSelect.getItems().get(i).getTitle());
                                optionSelect.getItems().get(i).setSelected(true);
                            }else {
                                optionSelect.getItems().get(i).setSelected(false);
                            }
                        }

                    }
                })
                        .setCancelText(selectViewHolder.itemView.getContext().getString(R.string.cancel))
                        .setSubmitText(selectViewHolder.itemView.getContext().getString(R.string.done))
                        .setSelectOptions(defaultSelection)
                        .setSubmitColor(Color.BLACK)
                        .setCancelColor(Color.RED)
                        .build();
                pvOptions.setPicker(listForPicker);

                selectViewHolder.itemView.setOnClickListener(v -> {
                    pvOptions.show();
//                    selectItemDialog.show(fragmentManager, "selectDialog");
//                    selectItemDialog.setUpPicker(optionSelect.getItems(), position, this);
                });

//                selectViewHolder.spOptionSelect.setAdapter(new SpinnerAdapter(optionSelect.getItems()));
                break;
            case LIST:
                Option optionList = (Option) data.get(position);
                ListViewHolder listViewHolder = (ListViewHolder) holder;
                listViewHolder.tvOptionSelect.setText(optionList.getTitle());
                listViewHolder.tvOptionPreview.setText(setTextToListOption(optionList));
                listViewHolder.itemView.setOnClickListener(
                        view -> mOnOptionsClickListener.onOptionListClick(optionList,
                                () -> notifyItemChanged(position)));
                break;
            case SWITCHER:
                Option optionSwitcher = (Option) data.get(position);
                SwitcherViewHolder switcherViewHolder = (SwitcherViewHolder) holder;
                switcherViewHolder.swcOptionSwitcher.setChecked(optionSwitcher.isValue());
                switcherViewHolder.swcOptionSwitcher.setOnCheckedChangeListener((compoundButton, b) -> {
                    optionSwitcher.setValue(b);
                });
                switcherViewHolder.tvOptionSwitcher.setText(optionSwitcher.getTitle());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (data.get(position).getViewType().equals(OptionsMapper.TYPE_LABEL)) {
            return LABEL;
        } else if (data.get(position).getViewType().equals(OptionsMapper.TYPE_SWITCHER)) {
            return SWITCHER;
        } else if (data.get(position).getViewType().equals(OptionsMapper.TYPE_LIST)) {
            return LIST;
        } else if (data.get(position).getViewType().equals(OptionsMapper.TYPE_SELECT)) {
            return SELECT;
        }

        return super.getItemViewType(position);
    }

    private String setTextToListOption(Option optionList) {
        String showItem = "";
        for (Item item : optionList.getItems()) {
            showItem = showItem.concat(item.isSelected() ?
                    showItem.length() > 0 ? ", " + item.getTitle() : item.getTitle()
                    : "");
        }
        return showItem;
    }

    @Override
    public void selectItem(int position) {
        notifyItemChanged(position);
    }

    public interface OnOptionsClickListener {
        void onOptionListClick(Option option, OnOptionsChangeListenerListener onOptionsChangeListenerListener);

        void onOptionSwitchClick(Option option);

        void onOptionSelectClick(Option option);
    }

    public interface OnOptionsChangeListenerListener {
        void onOptionItemChangeClick();
    }

}
