package com.theappbusiness.kc.io;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * TODO Add a class header comment
 */
public class ImageHelperImpl implements ImageHelper {
    private final Provider<ImageRequest> mImageRequestProvider;

    @Inject
    public ImageHelperImpl(Provider<ImageRequest> imageRequestProvider) {
        mImageRequestProvider = imageRequestProvider;
    }

    @Override
    public ImageRequest load(String url) {
        ImageRequest imageRequest = mImageRequestProvider.get();
        imageRequest.load(url);
        return imageRequest;
    }
}
