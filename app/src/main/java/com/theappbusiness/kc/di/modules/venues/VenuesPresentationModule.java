package com.theappbusiness.kc.di.modules.venues;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.theappbusiness.kc.di.qualifiers.ForApplication;
import com.theappbusiness.kc.di.scopes.PerFragment;
import com.theappbusiness.kc.view.venues.VenuesAdapter;
import com.theappbusiness.kc.view.venues.VenuesAdapterImpl;
import com.theappbusiness.kc.view.venues.VenuesPresentation;
import com.theappbusiness.kc.view.venues.VenuesPresentationImpl;

import dagger.Module;
import dagger.Provides;

/**
 * TODO Add a class header comment
 */
@Module
public class VenuesPresentationModule {

    @Provides
    @PerFragment
    VenuesPresentation providePresentation(VenuesPresentationImpl presentation) {
        return presentation;
    }

    @Provides
    @PerFragment
    VenuesAdapter provideAdapter(VenuesAdapterImpl adapter) {
        return adapter;
    }

    @Provides
    RecyclerView.LayoutManager provideLayoutManager(@ForApplication Context context) {
        return new LinearLayoutManager(context);
    }


}
