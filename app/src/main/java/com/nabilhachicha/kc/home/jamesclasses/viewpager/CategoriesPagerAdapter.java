package com.nabilhachicha.kc.home.jamesclasses.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.nabilhachicha.kc.items.itemlist.ItemsFragment;
import com.nabilhachicha.kc.model.Category;

import java.util.List;

/**
 * Created by voicuklein on 25/04/15.
 */
public class CategoriesPagerAdapter extends FragmentStatePagerAdapter {
    private List<Category> mCategories;

    public CategoriesPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setCategories(List<Category> categories){
        mCategories = categories;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mCategories == null ? 0 : mCategories.size() ;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCategories.get(position).getName();
    }

    @Override
    public Fragment getItem(int i) {
        return ItemsFragment.newInstance(mCategories.get(i).getName());
    }


    public int getColorWithAlpha(float alpha, int baseColor) {
        int a = Math.min(255, Math.max(0, (int) (alpha * 255))) << 24;
        int rgb = 0x00ffffff & baseColor;
        return a + rgb;
    }

}
