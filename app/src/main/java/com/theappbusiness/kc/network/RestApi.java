package com.theappbusiness.kc.network;

import com.theappbusiness.kc.model.CategoriesResponse;
import com.theappbusiness.kc.model.VenuesResponse;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Retrofit service interface definition
 */
public interface RestApi {
    @GET("/categories")
    CategoriesResponse getCategories();

    @GET("/categories/{id}/venues")
    VenuesResponse getVenuesByCategory(@Path("id") String category);
}
