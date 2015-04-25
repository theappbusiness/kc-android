package com.nabilhachicha.kc.items.itemlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nabilhachicha.kc.KcApp;
import com.nabilhachicha.kc.R;
import com.nabilhachicha.kc.model.POI;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by stephencolias on 25/04/15.
 */
public class ItemsRecyclerAdapter extends RecyclerView.Adapter<ItemsRecyclerAdapter.ViewHolder> {
    private List<POI> mDataset;

   private Picasso mPicasso;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mImageView;
        public TextView mTitleTextView;
        public TextView mDescriptionTextView;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            mImageView = (ImageView) itemLayoutView.findViewById(R.id.item_image);
            mTitleTextView = (TextView) itemLayoutView.findViewById(R.id.item_text);
            mDescriptionTextView = (TextView) itemLayoutView.findViewById(R.id.item_text);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ItemsRecyclerAdapter(List<POI> myDataset, Picasso picasso) {
        mDataset = myDataset;
        mPicasso = picasso;
    }

    // Create new views (invoked by the layout manager)
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
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        POI poi = mDataset.get(position);
        holder.mTitleTextView.setText(poi.getName());
        holder.mDescriptionTextView.setText(poi.getDescription());
        try {
            holder.mImageView.setImageBitmap(mPicasso.load(poi.getImgUrl()).get());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}