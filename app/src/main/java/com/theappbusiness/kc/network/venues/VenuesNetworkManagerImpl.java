package com.theappbusiness.kc.network.venues;

import com.theappbusiness.kc.controllers.venues.VenuesController;
import com.theappbusiness.kc.model.VenuesResponse;
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
public class VenuesNetworkManagerImpl implements VenuesNetworkManager {
    private final NetworkHelper mNetworkHelper;
    private final String mCategory;
    private final VenuesController mController;

    private Subscription mSubscription;

    @Inject
    public VenuesNetworkManagerImpl(NetworkHelper networkHelper, String category,
                                    VenuesController controller) {
        mNetworkHelper = networkHelper;
        mCategory = category;
        mController = controller;
    }

    @Override
    public void requestVenues() {
        mSubscription = buildVenuesObservable()
                .map(VenuesResponse::getVenues)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mController::onVenusLoaded, this::onError, this::onCompleted);
    }

    @Override
    public void cancelRequest() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
            mSubscription = null;
        }
    }

    private Observable<VenuesResponse> buildVenuesObservable() {
        return Observable.create(subscriber -> {
            subscriber.onNext(mNetworkHelper.getVenuesForCategory(mCategory));
            subscriber.onCompleted();
        });
    }

    private void onError(Throwable t) {
        Timber.e(t, "Error requesting venues for category %s", mCategory);
    }

    private void onCompleted() {
        mSubscription = null;
    }
}
