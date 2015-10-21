package com.theappbusiness.kc.di.modules.venues;

import com.theappbusiness.kc.controllers.venues.VenuesController;
import com.theappbusiness.kc.di.scopes.PerFragment;
import com.theappbusiness.kc.network.venues.VenuesNetworkManager;
import com.theappbusiness.kc.network.venues.VenuesNetworkManagerImpl;
import com.theappbusiness.kc.controllers.venues.VenuesFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Used in ItemFragment
 */
@Module
public class VenuesModule {
    private final VenuesFragment mFragment;
    private final String mCategory;

    public VenuesModule(VenuesFragment fragment, String category) {
        mFragment = fragment;
        mCategory = category;
    }

    @Provides
    @PerFragment
    VenuesController providesVenuesController() {
        return mFragment;
    }

    @Provides
    @PerFragment
    VenuesNetworkManager provideNetworkManager(VenuesNetworkManagerImpl networkManager) {
        return networkManager;
    }

    @Provides
    @PerFragment
    String provideCategory() {
        return mCategory;
    }
}

