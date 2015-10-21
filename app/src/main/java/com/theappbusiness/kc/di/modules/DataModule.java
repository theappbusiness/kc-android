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

import com.squareup.okhttp.OkHttpClient;
import com.theappbusiness.kc.di.scopes.PerApplication;
import com.theappbusiness.kc.utils.ConnectionUtils;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nabil Hachicha on 06/12/14.
 */
@Module
public final class DataModule {

    @Provides
    @PerApplication
    ConnectionUtils provideConnectionUtils(OkHttpClient client) {
        return new ConnectionUtils(client);
    }

}