package com.theappbusiness.kc.io;

import com.theappbusiness.kc.model.Venue;

import java.util.List;

import rx.Observable;

/**
 * Concrete interface for {@link Venue} controller. We cannot use generics directly in Dagger.
 */
public interface VenuesController extends GenericController<List<Venue>> {
    void showError();

    Observable<List<Venue>> queryBackend();

    void showContent(List<Venue> categories);

    void showVenue(Venue venue);
}
