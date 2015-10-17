package com.theappbusiness.kc.io;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Manages download of specific model
 */
public abstract class Manager {

    Subscription mSubscription;

    GenericController flowListener;

    public void onStart() {
        mSubscription =
                flowListener.queryBackend()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(flowListener::showContent, throwable1 -> flowListener
                                .showError());
    }

    public void onStop() {
       if (null != mSubscription) {
            mSubscription.unsubscribe();
        }
    }
}
