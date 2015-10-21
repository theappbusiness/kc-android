package com.theappbusiness.kc.view.venues;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.theappbusiness.kc.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VenueViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.venue_image)
    public ImageView mImageView;

    @Bind(R.id.venue_name)
    public TextView mTitleTextView;

    @Bind(R.id.venue_description)
    public TextView mDescriptionTextView;


    public VenueViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
