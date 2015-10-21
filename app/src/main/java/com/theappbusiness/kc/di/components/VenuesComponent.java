package com.theappbusiness.kc.di.components;

import com.theappbusiness.kc.di.modules.venues.VenuesModule;
import com.theappbusiness.kc.di.modules.venues.VenuesPresentationModule;
import com.theappbusiness.kc.di.scopes.PerFragment;
import com.theappbusiness.kc.controllers.venues.VenuesFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {VenuesModule.class, VenuesPresentationModule.class})
@PerFragment
public interface VenuesComponent {
    void inject(VenuesFragment fragment);
}
