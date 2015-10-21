package com.theappbusiness.kc.network.categories;

import com.theappbusiness.kc.controllers.categories.CategoriesController;
import com.theappbusiness.kc.model.CategoriesResponse;
import com.theappbusiness.kc.network.NetworkHelper;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * TODO Add a class header comment
 */
public class CategoriesNetworkManagerImpl implements CategoriesNetworkManager {
    private final NetworkHelper mNetworkHelper;
    private final CategoriesController mController;
    private Subscription mSubscription;

    @Inject
    public CategoriesNetworkManagerImpl(NetworkHelper networkHelper, CategoriesController controller) {
        mNetworkHelper = networkHelper;
        mController = controller;
    }

    @Override
    public void requestCategories() {
        mSubscription = createRequestObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError, this::onCompleted);
    }

    @Override
    public void cancelRequests() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
            mSubscription = null;
        }
    }


    private Observable<CategoriesResponse> createRequestObservable() {
        return Observable.create(subscriber -> {
            subscriber.onNext(mNetworkHelper.requestCategories());
            subscriber.onCompleted();
        });
    }

    private void onSuccess(CategoriesResponse response) {
        mController.onCategoriesLoaded(response.getCategories());
    }

    private void onError(Throwable t) {
        Timber.e(t, "Error requesting categories");
    }

    private void onCompleted() {
        mSubscription = null;
    }
}
