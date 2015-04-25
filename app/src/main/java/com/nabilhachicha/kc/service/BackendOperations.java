package com.nabilhachicha.kc.service;

import com.nabilhachicha.kc.model.CategoriesResponse;
import com.nabilhachicha.kc.model.VenuesResponse;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Nabil on 25/04/15.
 */
public interface BackendOperations {
    //GET /categories
    @GET("/553b6f49e2eb2f5d1890a11e")
    Observable<CategoriesResponse> getCategories();

    //553b6f82e2eb2f691890a11f
    //GET /categories/:id/venues`
    @GET("/{category}")
    Observable<VenuesResponse> getVenuesByCategory(@Path("category") String category);
//    List<POI> getVenueDetails ();
}
