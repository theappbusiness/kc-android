package com.theappbusiness.kc.view.venues;

import com.theappbusiness.kc.model.Venue;
import com.theappbusiness.kc.view.BaseView;

import java.util.List;

/**
 * TODO Add a class header comment
 */
public interface VenuesPresentation extends BaseView {
    void showVenues(List<Venue> venues);
}
