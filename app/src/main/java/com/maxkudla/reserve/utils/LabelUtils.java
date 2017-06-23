package com.maxkudla.reserve.utils;

public class LabelUtils {

    public static String handleLabels(String label){
        label = label.replaceAll("_", " ");
        return label.substring(0,1).toUpperCase() + label.substring(1).toLowerCase();
    }

}
