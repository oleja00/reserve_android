package com.maxkudla.reserve.models.options;

import java.util.ArrayList;
import java.util.List;

public class OptionsMapper {

    public static final String TYPE_SWITCHER = "Switcher";
    public static final String TYPE_SELECT = "Select";
    public static final String TYPE_LIST = "List";
    public static final String TYPE_LABEL = "Label";

    public static List<ItemOptions> map(List<Datum> data) {
        List<ItemOptions> optionsItemss = new ArrayList<>();
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setViewType(TYPE_LABEL);
                optionsItemss.add(data.get(i));
                if (data.get(i).getItems() != null){
                    for (int j = 0; j < data.get(i).getItems().size(); j++) {
                        data.get(i).getItems().get(j).setViewType(data.get(i).getItems().get(j).getType());
                        optionsItemss.add(data.get(i).getItems().get(j));
                    }
                }

            }
        }

        return optionsItemss;
    }

}
