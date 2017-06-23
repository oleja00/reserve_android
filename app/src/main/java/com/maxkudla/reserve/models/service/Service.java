package com.maxkudla.reserve.models.service;


import java.util.List;

public class Service {

    // service _id":"590cd46e385a308bd37e82f3","createdAt":"2017-05-05T19:37:18.483Z","updatedAt":"2017-05-05T19:43:24.663Z","latitude":48.51655960083,"longitude":35.048164367676,"gis_id":"70000001022678111_ge7d2ep8p64ACAAC9AI2GGG0Addg9g26G6G419B8G7I24A88rgewB4A8AC7IGG6G0J3C5B8C9938uvG44C17A9ABJ2667867HH39","__v":0,"user":{"_id":"590cd4bdf969902dae0b3a98","createdAt":"2017-05-05T19:38:37.417Z","updatedAt":"2017-05-05T19:38:37.417Z","phone":"380964854445","__v":0,"type":0},"category":"58f8c570e2d087083269971c","name":"Магазин-кафе","address_name":"Калиновая, 89а/1","options":{"cuisines":["58f9e0aa7866391ab6f554bc"],"smoking":["smoking","no_smoking"],"worktime":{"end":24,"start":10}},"about":"The restaurant is located in a historic Excelsior brick building dating from 1886. Creating a sense of the natural evolution of the building was a goal of the designer, Kara Karpenske of Kamarron Design, Inc. (KDI) of Edina. New rustic floors, tall booths, oversized chandeliers, and industrial lighting enhance the look of the exposed original brick. Antiqued mirrored panels set off the white marble design bar top and warm toned leather bar stools create an inviting space to enjoy Chef Eli’s crafted cocktail menu regionally sourced from such boutique distilleries as 45th Parallel Vodka (New Richmond, WI), North Shore Gin #11 (Lake Bluff, IL), Cedar Ridge Iowa Bourbon (Swisher, Iowa), and locally produced Bittercube Bitters (Minneapolis, MN). A large selection of wine and MN sourced tap beers complement the full bar.","subtitle":"Service subtitle","photos":[],"price":2,"rating":0,"available":true},
    private User user;
    private String _id;
    private List<String> photos;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}
