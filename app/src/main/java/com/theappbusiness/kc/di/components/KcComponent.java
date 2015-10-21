package com.theappbusiness.kc.di.components;

import com.theappbusiness.kc.KcApp;
import com.theappbusiness.kc.di.modules.CategoriesModule;
import com.theappbusiness.kc.di.modules.KcModule;
import com.theappbusiness.kc.di.modules.NetworkModule;
import com.theappbusiness.kc.di.modules.PersistenceModule;
import com.theappbusiness.kc.di.modules.RetrofitNetworkModule;
import com.theappbusiness.kc.di.modules.venue.VenueModule;
import com.theappbusiness.kc.di.scopes.PerApplication;

import dagger.Component;

/**
 * TODO Add a class header comment
 */
@PerApplication
@Component(modules = {KcModule.class, NetworkModule.class, RetrofitNetworkModule.class,
        PersistenceModule.class})
public interface KcComponent {
    void inject(KcApp application);

    CategoriesComponent plus(CategoriesModule module);

    VenueComponent plus(VenueModule module);
}
