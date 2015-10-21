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

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.theappbusiness.kc.KcApp;
import com.theappbusiness.kc.di.qualifiers.ForApplication;
import com.theappbusiness.kc.di.scopes.PerApplication;
import com.theappbusiness.kc.io.ImageHelper;
import com.theappbusiness.kc.io.ImageHelperImpl;
import com.theappbusiness.kc.io.ImageRequest;
import com.theappbusiness.kc.io.ImageRequestImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class KcModule {
    private final KcApp mApp;

    public KcModule(KcApp app) {
        mApp = app;
    }

    @Provides
    @PerApplication
    Application provideApplication() {
        return mApp;
    }

    @Provides
    @ForApplication
    @PerApplication
    Context provideContext() {
        return mApp;
    }

    @Provides
    @PerApplication
    Resources provideResources() {
        return mApp.getResources();
    }

    @Provides
    @PerApplication
    ImageHelper provideImageHelper(ImageHelperImpl imageHelper) {
        return imageHelper;
    }

    @Provides
    @PerApplication
    Picasso providePicasso(@ForApplication Context context, OkHttpClient client) {
        return new Picasso.Builder(context)
                .downloader(new OkHttpDownloader(client))
                .build();
    }

    @Provides
    ImageRequest provideImageRequest(ImageRequestImpl imageRequest) {
        return imageRequest;
    }
}
