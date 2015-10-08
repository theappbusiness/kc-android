package com.theappbusiness.kc.di.components;

import com.theappbusiness.kc.di.modules.CategoriesModule;
import com.theappbusiness.kc.view.categories.CategoriesActivity;

import dagger.Subcomponent;

/**
 * TODO Add a class header comment
 */
@Subcomponent(modules = {CategoriesModule.class})
public interface CategoriesComponent {
    void inject(CategoriesActivity activity);
}
