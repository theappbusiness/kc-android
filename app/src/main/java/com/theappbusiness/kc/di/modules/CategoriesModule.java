package com.theappbusiness.kc.di.modules;

import android.support.v4.app.FragmentManager;

import com.theappbusiness.kc.controllers.categories.CategoriesActivity;
import com.theappbusiness.kc.controllers.categories.CategoriesController;
import com.theappbusiness.kc.di.qualifiers.ForCategories;
import com.theappbusiness.kc.di.scopes.PerActivity;
import com.theappbusiness.kc.network.categories.CategoriesNetworkManager;
import com.theappbusiness.kc.network.categories.CategoriesNetworkManagerImpl;
import com.theappbusiness.kc.view.categories.CategoriesPagerAdapter;
import com.theappbusiness.kc.view.categories.CategoriesPagerAdapterImpl;
import com.theappbusiness.kc.view.categories.CategoriesPresentation;
import com.theappbusiness.kc.view.categories.CategoriesPresentationImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class CategoriesModule {
    private final CategoriesActivity mActivity;


    public CategoriesModule(CategoriesActivity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    CategoriesController providesCategoriesController() {
        return mActivity;
    }

    @Provides
    @PerActivity
    CategoriesPresentation providesPresentation(CategoriesPresentationImpl presentation) {
        return presentation;
    }

    @Provides
    @PerActivity
    CategoriesNetworkManager providesNetworkManager(CategoriesNetworkManagerImpl networkManager) {
        return networkManager;
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
        return mActivity.getSupportFragmentManager();
    }
}
