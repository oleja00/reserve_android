package com.maxkudla.reserve.models.service.offer;

public class OfferModel {

    private int taxi;
    private int cocktail;
    private double discount;

    public int getTaxi() {
        return taxi;
    }

    public void setTaxi(int taxi) {
        this.taxi = taxi;
    }

    public int getCocktail() {
        return cocktail;
    }

    public void setCocktail(int cocktail) {
        this.cocktail = cocktail;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
