package com.theappbusiness.kc.io;

import javax.inject.Inject;

/**
 * Manager for {@link com.theappbusiness.kc.model.Venue}
 */
public class VenuesManagerImpl extends Manager {

    @Inject
    public VenuesManagerImpl(VenuesController flowListener) {
        this.flowListener = flowListener;
    }

}
