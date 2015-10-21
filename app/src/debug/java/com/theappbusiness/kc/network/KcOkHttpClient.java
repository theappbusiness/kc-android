package com.theappbusiness.kc.network;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.Cache;

import javax.inject.Inject;

/**
 * TODO Add a class header comment
 */
public class KcOkHttpClient extends BaseOkHttpClient {
    /**
     * Creates an instance of KcOkHttpClient
     *
     * @param cache Cache used in the OKHttpClient
     **/
    @Inject
    public KcOkHttpClient(Cache cache) {
        super(cache);
        networkInterceptors()
                .add(new StethoInterceptor());
    }
}
