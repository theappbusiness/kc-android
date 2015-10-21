package com.theappbusiness.kc.view.itemlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.theappbusiness.kc.R;

/**
 * Created by swav on 17/10/15.
 */ // Provide a reference to the views for each data item
public class VenueViewHolder extends RecyclerView.ViewHolder {

    public ImageView mImageView;
    public TextView mTitleTextView;
    public TextView mDescriptionTextView;
    public FrameLayout mContainer;
    private View mMain;


    public VenueViewHolder(View itemLayoutView) {
        super(itemLayoutView);
        mMain = itemLayoutView;
        mContainer = (FrameLayout) itemLayoutView.findViewById(R.id.card_view);
        mImageView = (ImageView) itemLayoutView.findViewById(R.id.item_image);
        mTitleTextView = (TextView) itemLayoutView.findViewById(R.id.item_name);
        mDescriptionTextView = (TextView) itemLayoutView.findViewById(R.id.item_description);
    }

    public void setOnVenueClickListener(final OnItemClickListener listener) {
        mMain.setOnClickListener(v -> listener.onItemClick(getAdapterPosition()));
    }


}
