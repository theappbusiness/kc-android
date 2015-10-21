package com.theappbusiness.kc.di.modules.venue;

import android.app.FragmentManager;
import android.content.Context;

import com.theappbusiness.kc.controllers.venue.VenueActivity;
import com.theappbusiness.kc.controllers.venue.VenueController;
import com.theappbusiness.kc.di.qualifiers.ForActivity;
import com.theappbusiness.kc.di.scopes.PerActivity;
import com.theappbusiness.kc.model.Venue;
import com.theappbusiness.kc.view.venue.VenuePresentation;
import com.theappbusiness.kc.view.venue.VenuePresentationImpl;

import dagger.Module;
import dagger.Provides;

/**
 * TODO Add a class header comment
 */
@Module
public class VenueModule {
    private final VenueActivity mActivity;
    private final Venue mVenue;

    public VenueModule(VenueActivity activity, Venue venue) {
        mActivity = activity;
        mVenue = venue;
    }

    @Provides
    @PerActivity
    @ForActivity
    Context provideContext() {
        return mActivity;
    }

    @Provides
    @PerActivity
    FragmentManager provideFragmentManager() {
        return mActivity.getFragmentManager();
    }

    @Provides
    @PerActivity
    VenuePresentation providePresentation(VenuePresentationImpl presentation) {
        return presentation;
    }

    @Provides
    @PerActivity
    VenueController provideController() {
        return mActivity;
    }

    @Provides
    @PerActivity
    Venue provideVenue() {
        return mVenue;
    }

}
