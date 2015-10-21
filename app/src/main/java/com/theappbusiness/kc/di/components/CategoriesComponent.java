package com.theappbusiness.kc.di.components;

import com.theappbusiness.kc.controllers.categories.CategoriesActivity;
import com.theappbusiness.kc.di.modules.CategoriesModule;
import com.theappbusiness.kc.di.modules.venues.VenuesModule;
import com.theappbusiness.kc.di.scopes.PerActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {CategoriesModule.class})
@PerActivity
public interface CategoriesComponent {
    VenuesComponent plus(VenuesModule module);

    void inject(CategoriesActivity activity);
}
