package com.theappbusiness.kc.io;

import android.widget.ImageView;

/**
 * TODO Add a class header comment
 */
public interface ImageRequest {
    ImageRequest load(String url);

    ImageRequest resize(int screenWidth, int screenHeight);

    ImageRequest centerInside();

    void into(ImageView view);
}
