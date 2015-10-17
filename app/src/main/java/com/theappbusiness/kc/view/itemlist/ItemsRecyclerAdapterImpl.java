package com.theappbusiness.kc.view.itemlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.theappbusiness.kc.R;
import com.theappbusiness.kc.io.VenuesController;
import com.theappbusiness.kc.model.Venue;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by stephencolias on 25/04/15.
 */
public class ItemsRecyclerAdapterImpl extends RecyclerView.Adapter<VenueViewHolder> implements ItemsRecyclerAdapter {

    private List<Venue> mDataset = new ArrayList<>();
    private Picasso mPicasso;

    private int lastPosition = -1;

    @Inject
    VenuesController venuesController;

    OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int id) {
            Venue venue = mDataset.get(id);
            venuesController.showVenue(venue);
        }
    };


    @Inject
    public ItemsRecyclerAdapterImpl(Picasso picasso, VenuesController venuesController) {
        mPicasso = picasso;
    }

    @Override
    public void setData(List<Venue> venues) {
        mDataset = venues;
        notifyDataSetChanged();
    }


    // Create new views
    @Override
    public VenueViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        VenueViewHolder vh = new VenueViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(VenueViewHolder holder, int position) {
        // - get element from the dataset at this position
        // - setData the contents of the view with this element
        Venue venue = mDataset.get(position);
        holder.mTitleTextView.setText(venue.getName());
        holder.mDescriptionTextView.setText(venue.getDescription());
        mPicasso.with(holder.mImageView.getContext()).load(venue.getImageUrl()).into(holder.mImageView);
        setAnimation(holder.mContainer, position);
        holder.setOnVenueClickListener(onItemClickListener);
    }


    // Return the size of the dataset
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), R.anim.slide_in_from_bottom);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(VenueViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.mContainer.clearAnimation();
    }
}