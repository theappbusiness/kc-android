package com.theappbusiness.kc.view.categories;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.theappbusiness.kc.model.Category;

import java.util.List;

/**
 * Created by swav on 08/10/15.
 */
public abstract class CategoriesPagerAdapter extends FragmentStatePagerAdapter {

    public CategoriesPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public abstract void setCategories(List<Category> categories);

    public abstract Category getCategory(int position);
}
