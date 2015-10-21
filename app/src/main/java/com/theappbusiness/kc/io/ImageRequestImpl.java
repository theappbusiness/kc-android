package com.theappbusiness.kc.io;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import javax.inject.Inject;

/**
 * TODO Add a class header comment
 */
public class ImageRequestImpl implements ImageRequest {
    private final Picasso mPicasso;
    private RequestCreator mRequestCreator;

    @Inject
    ImageRequestImpl(Picasso picasso) {
        mPicasso = picasso;
    }

    public ImageRequest load(String url) {
        mRequestCreator = mPicasso.load(url);
        return this;
    }

    @Override
    public ImageRequest resize(int screenWidth, int screenHeight) {
        mRequestCreator.resize(screenWidth, screenHeight);
        return this;
    }

    @Override
    public ImageRequest centerInside() {
        mRequestCreator.centerInside();
        return this;
    }

    public void into(ImageView view) {
        mRequestCreator.into(view);
    }
}
