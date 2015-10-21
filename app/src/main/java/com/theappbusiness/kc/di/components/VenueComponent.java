package com.theappbusiness.kc.di.components;

import com.theappbusiness.kc.controllers.venue.VenueActivity;
import com.theappbusiness.kc.di.modules.venue.VenueModule;
import com.theappbusiness.kc.di.scopes.PerActivity;

import dagger.Subcomponent;

/**
 * TODO Add a class header comment
 */
@PerActivity
@Subcomponent(modules = {VenueModule.class})
public interface VenueComponent {
    void inject(VenueActivity activity);
}
