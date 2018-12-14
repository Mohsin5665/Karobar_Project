package com.vcodewise.karobarlist.models;

import java.util.ArrayList;
import java.util.List;

public class BusinessItem {

    private int itemId;
    private String categoryId;
    private String categoryName;
    private String name;
    private String description;
    private String address;
    private String number;
    private String latitude;
    private String longitude;
    private float rating;
    private int noofRating;
    private int noofReviews;
    private boolean isFav;
    private List<String> imagesList;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNoofRating() {
        return noofRating;
    }

    public void setNoofRating(int noofRating) {
        this.noofRating = noofRating;
    }

    public int getNoofReviews() {
        return noofReviews;
    }

    public void setNoofReviews(int noofReviews) {
        this.noofReviews = noofReviews;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public List<String> getImagesList() {
        if (imagesList == null)
            imagesList = new ArrayList<String>();
        return imagesList;
    }

    public void setImagesList(List<String> imagesList) {
        this.imagesList = imagesList;
    }
}
