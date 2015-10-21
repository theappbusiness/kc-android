package com.theappbusiness.kc.network;

import com.theappbusiness.kc.model.CategoriesResponse;
import com.theappbusiness.kc.model.VenuesResponse;

import javax.inject.Inject;

import retrofit.RestAdapter;

/**
 * TODO Add a class header comment
 */
public class RetrofitNetworkHelper implements NetworkHelper {
    private final RestApi mRestApi;

    @Inject
    public RetrofitNetworkHelper(RestAdapter restAdapter) {
        mRestApi = restAdapter.create(RestApi.class);
    }

    @Override
    public CategoriesResponse requestCategories() {
        return mRestApi.getCategories();
    }

    @Override
    public VenuesResponse getVenuesForCategory(String category) {
        return mRestApi.getVenuesByCategory(category);
    }
}
