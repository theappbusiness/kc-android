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


package com.theappbusiness.kc.di;

import android.app.Application;

import com.theappbusiness.kc.KcApp;
import com.theappbusiness.kc.view.categories.CategoriesActivity;
import com.theappbusiness.kc.ItemDetailActivity;
import com.theappbusiness.kc.view.itemlist.ItemsFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nabil on 06/12/14.
 */
@Module(
        includes = {
                DataModule.class
        },
        injects = {
                KcApp.class,
                ItemDetailActivity.class,
                ItemsFragment.class,
                CategoriesActivity.class,
        }
)
public final class KcModule {
    private final KcApp app;

    public KcModule(KcApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }
}