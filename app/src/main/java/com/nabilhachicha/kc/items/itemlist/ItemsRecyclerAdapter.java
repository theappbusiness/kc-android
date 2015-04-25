package com.nabilhachicha.kc.items.itemlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nabilhachicha.kc.R;
import com.nabilhachicha.kc.model.Venue;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by stephencolias on 25/04/15.
 */
public class ItemsRecyclerAdapter extends RecyclerView.Adapter<ItemsRecyclerAdapter.ViewHolder> {

    private static List<Venue> mDataset;
    private Picasso mPicasso;

    private OnItemClickListener mItemClickListener;

    // Allows to remember the last item shown on screen
    private int mLastPosition = -1;


    // Provide a reference to the views for each data item
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView mImageView;
        public TextView mTitleTextView;
        public TextView mDescriptionTextView;
        public FrameLayout mContainer;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            mContainer = (FrameLayout) itemLayoutView.findViewById(R.id.card_view);
            mImageView = (ImageView) itemLayoutView.findViewById(R.id.item_image);
            mTitleTextView = (TextView) itemLayoutView.findViewById(R.id.item_name);
            mDescriptionTextView = (TextView) itemLayoutView.findViewById(R.id.item_description);
            itemLayoutView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(mDataset.get(getPosition()));
            }
        }

    }

    public interface OnItemClickListener {
        void onItemClick(Venue poi);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public ItemsRecyclerAdapter(List<Venue> myDataset, Picasso picasso) {
        mDataset = myDataset;
        mPicasso = picasso;
    }

    // Create new views
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from the dataset at this position
        // - replace the contents of the view with this element
        Venue venue = mDataset.get(position);
        holder.mTitleTextView.setText(venue.getName());
        holder.mDescriptionTextView.setText(venue.getDescription());
        mPicasso.with(holder.mImageView.getContext()).load(venue.getImageUrl()).into(holder.mImageView);
        setAnimation(holder.mContainer, position);
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
        if (position > mLastPosition) {
            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), R.anim.slide_in_from_bottom);
            viewToAnimate.startAnimation(animation);
            mLastPosition = position;
        }
    }
}