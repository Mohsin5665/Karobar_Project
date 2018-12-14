package com.vcodewise.karobarlist.utils;

import com.vcodewise.karobarlist.models.BusinessItem;
import com.vcodewise.karobarlist.models.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class DataHolder {

    private static final DataHolder ourInstance = new DataHolder();
    private List<CategoryItem> categoryItemList;
    private List<BusinessItem> businessItemList;

    public static DataHolder getInstance() {
        return ourInstance;
    }

    private DataHolder() {
    }


    public List<CategoryItem> getCategoryItemList() {
        if(categoryItemList == null)
            categoryItemList = new ArrayList<CategoryItem>();
        return categoryItemList;
    }

    public void setCategoryItemList(List<CategoryItem> categoryItemList) {
        this.categoryItemList = categoryItemList;
    }

    public List<BusinessItem> getBusinessItemList() {
        if(businessItemList == null)
            businessItemList = new ArrayList<BusinessItem>();
        return businessItemList;
    }

    public void setBusinessItemList(List<BusinessItem> businessItemList) {
        this.businessItemList = businessItemList;
    }
}
