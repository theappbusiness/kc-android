package com.theappbusiness.kc.di.modules;

import android.support.v4.app.FragmentManager;

import com.theappbusiness.kc.di.scopes.PerActivity;
import com.theappbusiness.kc.io.CategoriesController;
import com.theappbusiness.kc.io.CategoriesManagerImpl;
import com.theappbusiness.kc.io.Manager;
import com.theappbusiness.kc.view.categories.CategoriesActivity;
import com.theappbusiness.kc.view.categories.CategoriesPagerAdapter;
import com.theappbusiness.kc.view.categories.CategoriesPagerAdapterImpl;
import com.theappbusiness.kc.view.categories.CategoriesPresentation;
import com.theappbusiness.kc.view.categories.CategoriesPresentationImpl;

import dagger.Module;
import dagger.Provides;

/**
 * TODO Add a class header comment
 */
@Module
public class CategoriesModule {


    private CategoriesActivity mCategoriesActivity;


    public CategoriesModule(CategoriesActivity categoriesActivity) {
        mCategoriesActivity = categoriesActivity;
    }

    @Provides
    @PerActivity
    CategoriesPresentation providesCategoriesPresentation(CategoriesPresentationImpl
                                                                  categoriesPresentation) {
        return categoriesPresentation;
    }

    @Provides
    @PerActivity
    CategoriesPagerAdapter providesCategoriesAdapter(CategoriesPagerAdapterImpl
                                                             categoriesPagerAdapter) {
        return categoriesPagerAdapter;
    }

    @Provides
    @PerActivity
    FragmentManager providesFragmentManager() {
        return mCategoriesActivity.getSupportFragmentManager();
    }

    @Provides
    @PerActivity
    Manager providesDataLoaderHelper(CategoriesManagerImpl helper) {
        return helper;
    }

    @Provides
    @PerActivity
    CategoriesController providesCategoriesController() {
        return mCategoriesActivity;
    }


}
