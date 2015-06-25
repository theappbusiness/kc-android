package com.theappbusiness.kc.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nabil on 25/04/15.
 */
public class CategoriesResponse {
    @SerializedName("categories")
    private List<Category> categories;

    public List<Category> get() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
