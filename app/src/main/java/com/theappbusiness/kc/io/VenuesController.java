package com.theappbusiness.kc.io;

import com.theappbusiness.kc.model.Venue;

import java.util.List;

import rx.Observable;

/**
 * Created by swav on 09/10/15.
 */
public interface VenuesController extends GenericController<List<Venue>> {
    void showError();

    Observable<List<Venue>> queryBackend();

    void showContent(List<Venue> categories);
}
