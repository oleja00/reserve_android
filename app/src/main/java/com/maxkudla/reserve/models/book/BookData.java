package com.maxkudla.reserve.models.book;

import java.util.List;

public class BookData {

    private String _id;
    private String createdAt;
    private String updatedAt;
    private double latitude;
    private double longitude;
    private String gis_id;
    private String user;
    private String category;
    private String name;
    private String address_name;
    private int price;
    private int rating;
    private boolean available;
    private List<BookOptions> options;
    private String about;
    private String subtitle;
    private List<String> photos;

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public List<BookOptions> getOptions() {
        return options;
    }

    public void setOptions(List<BookOptions> options) {
        this.options = options;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getGis_id() {
        return gis_id;
    }

    public void setGis_id(String gis_id) {
        this.gis_id = gis_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress_name() {
        return address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return " _id = " + _id +
                " createdAt = " + createdAt +
                " updatedAt = " + updatedAt +
                " latitude = " + latitude +
                " longitude = " + longitude +
                " gis_id = " + gis_id +
                " user = " + user +
                " category = " + category +
                " name = " + name +
                " address_name = " + address_name +
                " price = " + price +
                " rating = " + rating +
                " available = " + available;
    }
}
