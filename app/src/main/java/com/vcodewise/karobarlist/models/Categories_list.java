package com.vcodewise.karobarlist.models;

public class Categories_list {
    private String Name;
    private String imageUrl;

    public Categories_list(String name, String imageUrl){

        this.Name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImg_url(String img_url) {
        this.imageUrl = img_url;
    }
}
