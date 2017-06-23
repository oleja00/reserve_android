package com.maxkudla.reserve.models.parametrs;

import android.support.annotation.NonNull;

import com.maxkudla.reserve.utils.HanziToPinyin;

/**
 * Created by Developer on 30.04.2017.
 */

public class ParametrWrapper implements Comparable<ParametrWrapper>{
    private Parametr parametr;
    private boolean isCheck;

    public ParametrWrapper(Parametr parametr, boolean isCheck) {
        this.parametr = parametr;
        this.isCheck = isCheck;
    }

    public Parametr getParametr() {
        return parametr;
    }

    public void setParametr(Parametr parametr) {
        this.parametr = parametr;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    @Override
    public int compareTo(@NonNull ParametrWrapper another) {
        return HanziToPinyin.getFirstPinYinChar(parametr.getName()).compareTo(HanziToPinyin.getFirstPinYinChar(another.getParametr().getName()));
    }
}
