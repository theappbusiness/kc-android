/*
 * Copyright (C) 2015 The App Business.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.theappbusiness.kc.di.modules;

import com.theappbusiness.kc.BuildConfig;
import com.theappbusiness.kc.di.qualifiers.ForApiUrl;
import com.theappbusiness.kc.service.BackendOperations;
import com.squareup.okhttp.OkHttpClient;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import com.theappbusiness.kc.di.scopes.PerApplication;


@Module
public final class ApiModule {
    public static final String PRODUCTION_API_URL = BuildConfig.BACKEND_ENDPOINT;


    @Provides
    @ForApiUrl
    @PerApplication
    String provideApiUrl() {
        return PRODUCTION_API_URL;
    }

    @Provides
    @PerApplication
    Endpoint provideEndpoint(@ForApiUrl String apiUrl) {
        return Endpoints.newFixedEndpoint(apiUrl);
    }

    @Provides
    Client provideClient(OkHttpClient client) {
        return new OkClient(client);
    }

    @Provides
    @PerApplication
    RestAdapter provideRestAdapter(Endpoint endpoint, Client client) {
        RestAdapter.Builder builder = new RestAdapter.Builder() //
                .setClient(client) //
                .setEndpoint(endpoint); //
                if(BuildConfig.DEBUG) {
                    builder.setLogLevel(RestAdapter.LogLevel.BASIC);
                }
                return builder.build();
    }

    @Provides
    @PerApplication
    BackendOperations provideNewBackendService(RestAdapter restAdapter) {
        return restAdapter.create(BackendOperations.class);
    }
}
