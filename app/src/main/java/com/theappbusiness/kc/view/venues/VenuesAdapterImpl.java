package com.theappbusiness.kc.view.venues;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.theappbusiness.kc.R;
import com.theappbusiness.kc.controllers.venues.VenuesController;
import com.theappbusiness.kc.io.ImageHelper;
import com.theappbusiness.kc.model.Venue;

import java.util.List;

import javax.inject.Inject;

public class VenuesAdapterImpl extends VenuesAdapter {
    private final VenuesController mController;
    private final ImageHelper mImageHelper;

    private List<Venue> mVenues;
    private int mLastAnimViewPosition = -1;


    @Inject
    public VenuesAdapterImpl(VenuesController controller, ImageHelper imageHelper) {
        mController = controller;
        mImageHelper = imageHelper;
    }

    @Override
    public void setVenues(List<Venue> venues) {
        mVenues = venues;
        notifyDataSetChanged();
    }


    @Override
    public VenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_venue_item, parent, false);
        return new VenueViewHolder(v);
    }

    @Override
    public void onBindViewHolder(VenueViewHolder holder, int position) {
        Venue venue = mVenues.get(position);
        holder.mTitleTextView.setText(venue.getName());
        holder.mDescriptionTextView.setText(venue.getDescription());
        mImageHelper.load(venue.getImageUrl())
                .into(holder.mImageView);

        setAnimation(holder.itemView, position);
        holder.itemView.setOnClickListener(view -> mController.onVenueClicked(venue));
    }


    @Override
    public int getItemCount() {
        return mVenues == null ? 0 : mVenues.size();
    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > mLastAnimViewPosition) {
            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), R.anim.slide_in_from_bottom);
            viewToAnimate.startAnimation(animation);
            mLastAnimViewPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(VenueViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }
}