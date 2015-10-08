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

package com.theappbusiness.kc;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.theappbusiness.kc.di.components.DaggerKcComponent;
import com.theappbusiness.kc.di.components.KcComponent;
import com.theappbusiness.kc.di.modules.KcModule;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Nabil Hachicha on 06/12/14.
 */
public class KcApp extends Application {
    private KcComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        buildObjectGraphAndInject();
    }

    public void buildObjectGraphAndInject() {
        mComponent = DaggerKcComponent.builder()
                .kcModule(new KcModule(this))
                .build();
        mComponent.inject(this);
    }

    public KcComponent getComponent() {
        return mComponent;
    }

    public static KcApp get(Context context) {
        return (KcApp) context.getApplicationContext();
    }
}
