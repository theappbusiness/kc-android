package com.theappbusiness.kc.di.modules;

import com.theappbusiness.kc.di.qualifiers.ForVenues;
import com.theappbusiness.kc.di.scopes.PerActivity;
import com.theappbusiness.kc.io.Manager;
import com.theappbusiness.kc.io.VenuesController;
import com.theappbusiness.kc.io.VenuesManagerImpl;
import com.theappbusiness.kc.view.itemlist.ItemsFragment;
import com.theappbusiness.kc.view.itemlist.ItemsRecyclerAdapter;
import com.theappbusiness.kc.view.itemlist.ItemsRecyclerAdapterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Used in ItemFragment
 */
@Module
public class VenuesModule {

    ItemsFragment mFragment;

   public VenuesModule(ItemsFragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @PerActivity
    VenuesController providesVenuesController() {
        return mFragment;
    }

    @Provides
    @PerActivity
    @ForVenues
    Manager providesVenuesManager(VenuesManagerImpl helper) {
        return helper;
    }

    @Provides
    @PerActivity
    ItemsRecyclerAdapter providesItemRecyclerAdapter(ItemsRecyclerAdapterImpl adapter) {
        return adapter;
    }


}
