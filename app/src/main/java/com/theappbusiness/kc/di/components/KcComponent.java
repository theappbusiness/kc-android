package com.theappbusiness.kc.di.components;

import com.theappbusiness.kc.KcApp;
import com.theappbusiness.kc.di.DataModule;
import com.theappbusiness.kc.di.modules.ApiModule;
import com.theappbusiness.kc.di.modules.CategoriesModule;
import com.theappbusiness.kc.di.modules.KcModule;
import com.theappbusiness.kc.di.scopes.PerApplication;

import dagger.Component;

/**
 * TODO Add a class header comment
 */
@PerApplication
@Component(modules = {KcModule.class, DataModule.class, ApiModule.class})
public interface KcComponent {
    void inject(KcApp application);
    CategoriesComponent plus(CategoriesModule module);
}
