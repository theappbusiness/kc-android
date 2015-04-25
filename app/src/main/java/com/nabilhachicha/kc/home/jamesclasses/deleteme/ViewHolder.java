package com.nabilhachicha.kc.home.jamesclasses.deleteme;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nabilhachicha.kc.R;

/**
 * Created by jamesscott on 25/04/15.
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    private final TextView mItemTextView;

    public ViewHolder(final View parent, TextView itemTextView) {
        super(parent);
        mItemTextView = itemTextView;
    }

    public static ViewHolder newInstance(View parent) {
        TextView itemTextView = (TextView) parent.findViewById(R.id.itemTextView);
        return new ViewHolder(parent, itemTextView);
    }

    public void setItemText(CharSequence text) {
        mItemTextView.setText(text);
    }
}
