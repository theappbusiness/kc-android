package com.theappbusiness.kc.view.categories;

import com.theappbusiness.kc.model.Category;

import java.util.List;

/**
 * Created by swav on 08/10/15.
 */
public interface CategoriesPagerAdapter {
    void setCategories(List<Category> categories);
    Category getCategory(int position);
}
