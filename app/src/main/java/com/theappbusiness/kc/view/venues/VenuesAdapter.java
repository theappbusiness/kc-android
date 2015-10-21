package com.theappbusiness.kc.view.venues;

import android.support.v7.widget.RecyclerView;

import com.theappbusiness.kc.model.Venue;

import java.util.List;

public abstract class VenuesAdapter extends RecyclerView.Adapter<VenueViewHolder> {
    public abstract void setVenues(List<Venue> venues);
}
