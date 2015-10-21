package com.theappbusiness.kc.di.components;

import com.theappbusiness.kc.di.modules.CategoriesModule;
import com.theappbusiness.kc.di.modules.VenuesModule;
import com.theappbusiness.kc.di.scopes.PerActivity;
import com.theappbusiness.kc.view.categories.CategoriesActivity;
import com.theappbusiness.kc.view.itemlist.ItemsFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {CategoriesModule.class})
@PerActivity
public interface CategoriesComponent {
    VenuesComponent plus(VenuesModule module);
    void inject(CategoriesActivity activity);
}
