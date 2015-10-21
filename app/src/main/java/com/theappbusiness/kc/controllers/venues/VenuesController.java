package com.theappbusiness.kc.controllers.venues;

import com.theappbusiness.kc.model.Venue;

import java.util.List;

/**
 * Concrete interface for {@link Venue} controller. We cannot use generics directly in Dagger.
 */
public interface VenuesController {
    void onVenusLoaded(List<Venue> categories);

    void onVenueClicked(Venue venue);
}
