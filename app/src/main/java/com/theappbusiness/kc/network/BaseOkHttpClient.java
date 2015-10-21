package com.theappbusiness.kc.network;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * Base custom OkHttpClient implementation. Implemented like this to inject the Stetho interceptor
 * only on debug mode
 */
public class BaseOkHttpClient extends OkHttpClient {
    /**
     * Connection timeout in seconds
     */
    private static final int CONNECTION_TIMEOUT = 10;
    /**
     * Read timeout in seconds
     */
    private static final int READ_TIMEOUT = 20;

    /**
     * Creates an instance of CommonOkHttpClient
     *
     * @param cache Cache used in the OKHttpClient
     **/
    public BaseOkHttpClient(Cache cache) {
        super();
        setCache(cache);
        setConnectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        setReadTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        setFollowRedirects(false);
        setFollowSslRedirects(false);
    }
}
