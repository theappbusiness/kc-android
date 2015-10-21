package com.theappbusiness.kc.di.modules;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.theappbusiness.kc.BuildConfig;
import com.theappbusiness.kc.di.qualifiers.ForApplication;
import com.theappbusiness.kc.di.scopes.PerApplication;
import com.theappbusiness.kc.network.KcOkHttpClient;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

/**
 * TODO Add a class header comment
 */
@Module
public class RetrofitNetworkModule {
    /**
     * Cache size for the http client in bytes
     **/
    private static final long HTTP_CACHE_SIZE = 100 * 1024 * 1024; //100 Mb

    @Provides
    @PerApplication
    RestAdapter provideRestBuilder(Client client, Converter converter) {
        return new RestAdapter.Builder()
                .setClient(client)
                .setConverter(converter)
                .setEndpoint(BuildConfig.BACKEND_ENDPOINT)
                .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .build();
    }

    @Provides
    @PerApplication
    Converter provideConverter() {
        GsonBuilder builder = new GsonBuilder();
        return new GsonConverter(builder.create());
    }

    @PerApplication
    @Provides
    Client provideHttpClient(OkHttpClient client) {
        return new OkClient(client);
    }

    @Provides
    @PerApplication
    OkHttpClient provideOkHttpClient(KcOkHttpClient client) {
        return client;
    }

    @PerApplication
    @Provides
    Cache provideOkHttpCache(@ForApplication Context context) {
        return new Cache(context.getCacheDir(), HTTP_CACHE_SIZE);
    }

}
