package com.nabilhachicha.kc.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nabil on 25/04/15.
 */
public class VenuesResponse {
    @SerializedName("items")
    private List<Venue> venues;

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }
}
