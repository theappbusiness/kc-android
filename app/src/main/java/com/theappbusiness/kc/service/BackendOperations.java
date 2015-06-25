package com.theappbusiness.kc.service;

import com.theappbusiness.kc.model.CategoriesResponse;
import com.theappbusiness.kc.model.VenuesResponse;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Nabil on 25/04/15.
 */
public interface BackendOperations {
    @GET("/categories")
    Observable<CategoriesResponse> getCategories();

    @GET("/categories/{id}/venues")
    Observable<VenuesResponse> getVenuesByCategory(@Path("id") String category);
}
