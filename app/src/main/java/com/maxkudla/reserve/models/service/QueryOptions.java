package com.maxkudla.reserve.models.service;

import java.util.List;

public class QueryOptions {

    private float worktill;
    private String smoking;
    private boolean hookah;
    private List<String> cuisines;

    public float getWorktill() {
        return worktill;
    }

    public void setWorktill(float worktill) {
        this.worktill = worktill;
    }

    public String getSmoking() {
        return smoking;
    }

    public void setSmoking(String smoking) {
        this.smoking = smoking;
    }

    public boolean isHookah() {
        return hookah;
    }

    public void setHookah(boolean hookah) {
        this.hookah = hookah;
    }

    public List<String> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<String> cuisines) {
        this.cuisines = cuisines;
    }
}
