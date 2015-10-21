package com.theappbusiness.kc.view.categories;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.theappbusiness.kc.model.Category;
import com.theappbusiness.kc.controllers.venues.VenuesFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by voicuklein on 25/04/15.
 */
public class CategoriesPagerAdapterImpl extends CategoriesPagerAdapter {
    private List<Category> mCategories;

    @Inject
    public CategoriesPagerAdapterImpl(FragmentManager fm) {
        super(fm);
    }

    @Override
    public void setCategories(List<Category> categories) {
        mCategories = categories;
        notifyDataSetChanged();
    }

    @Override
    public Category getCategory(int position) {
        return mCategories.get(position);
    }

    @Override
    public int getCount() {
        return mCategories == null ? 0 : mCategories.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCategories.get(position).getName();
    }

    @Override
    public Fragment getItem(int i) {
        return VenuesFragment.newInstance(mCategories.get(i).getId());
    }

}
