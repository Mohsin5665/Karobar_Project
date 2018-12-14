package com.vcodewise.karobarlist.models;

public class ItemListing {

    private String categoryName;
    private String address;

    public ItemListing(String categoryName, String address, float rating, long noofReviews, String imageURL) {
        this.categoryName = categoryName;
        this.address = address;
        this.rating = rating;
        this.noofReviews = (int) noofReviews;
        ImageURL = imageURL;
    }

    private float rating;
    private int noofReviews;
    private String ImageURL;



    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNoofReviews() {
        return noofReviews;
    }

    public void setNoofReviews(int noofReviews) {
        this.noofReviews = noofReviews;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }
}
