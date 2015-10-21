package com.theappbusiness.kc.view.categories;

import android.view.View;

import com.theappbusiness.kc.model.Category;

import java.util.List;

/**
 * Created by swav on 08/10/15.
 */
public interface CategoriesPresentation {

    void init(View view);

    void showCategories(List<Category> data);
}
