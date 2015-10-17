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

package com.theappbusiness.kc.io;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nabil Hachicha on 10/12/14.
 * Helper to orchestrate the dynamic of getting the content
 * handle use of cache, errors et update in the background thread
 * <p>
 * //1- show SplashScreen by default (UI Thread)
 * //2- check cache (BG Thread)
 * //3- cache available, (hide splash, show cache & try to update in background) (UI Thread/BG Thread)
 * //3.1 show content & start update in background
 * //4- no cache, keep showing splash, (check internet & update)
 * //4.1 Internet available -> start updating -> show content
 * //4.1 Internet available -> start updating -> error -> show error screen
 * //4.1 Internet not available -> show error screen
 */
public class CategoriesManagerImpl implements Manager {


    CategoriesController flowListener;

    Subscription mSubscription;

    @Inject
    public CategoriesManagerImpl(CategoriesController flowListener) {
        this.flowListener = flowListener;
    }

    @Override
    public void onStart() {
        mSubscription =
                flowListener.queryBackend()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(flowListener::showContent, throwable1 -> flowListener
                                .showError());
    }

    @Override
    public void onStop() {
        if (null != mSubscription) {
            mSubscription.unsubscribe();
        }
    }
}
