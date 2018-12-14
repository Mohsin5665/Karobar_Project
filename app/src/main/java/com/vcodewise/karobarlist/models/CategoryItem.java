package com.vcodewise.karobarlist.models;

public class CategoryItem {

    private String title;
    private String noofItems;
    private String imageUrl;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoofItems() {
        return this.noofItems;
    }

    public void setNoofItems(String noofItems) {
        this.noofItems = noofItems;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
