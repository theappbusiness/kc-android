package com.theappbusiness.kc.controllers.venue;

import com.theappbusiness.kc.model.Venue;

/**
 * TODO Add a class header comment
 */
public interface VenueController {
    void onBackPressed();

    void onGetDirectionsClicked(Venue venue);

    void onCallClicked(String phone);

    void onWebsiteClicked(String websiteUrl);
}
