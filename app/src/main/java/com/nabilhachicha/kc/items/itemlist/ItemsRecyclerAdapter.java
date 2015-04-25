package com.nabilhachicha.kc.items.itemlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nabilhachicha.kc.R;
import com.nabilhachicha.kc.model.POI;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by stephencolias on 25/04/15.
 */
public class ItemsRecyclerAdapter extends RecyclerView.Adapter<ItemsRecyclerAdapter.ViewHolder> {

    private List<POI> mDataset;
    private Picasso mPicasso;

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTitleTextView;
        public TextView mDescriptionTextView;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            mImageView = (ImageView) itemLayoutView.findViewById(R.id.item_image);
            mTitleTextView = (TextView) itemLayoutView.findViewById(R.id.item_name);
            mDescriptionTextView = (TextView) itemLayoutView.findViewById(R.id.item_description);
        }
    }

    public ItemsRecyclerAdapter(List<POI> myDataset, Picasso picasso) {
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
        POI poi = mDataset.get(position);
        holder.mTitleTextView.setText(poi.getName());
        holder.mDescriptionTextView.setText(poi.getDescription());
        mPicasso.with(holder.mImageView.getContext()).load(poi.getImgUrl()).into(holder.mImageView);
    }

    // Return the size of the dataset
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}