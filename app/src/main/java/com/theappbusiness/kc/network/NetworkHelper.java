package com.theappbusiness.kc.network;

import com.theappbusiness.kc.model.CategoriesResponse;
import com.theappbusiness.kc.model.VenuesResponse;

/**
 * TODO Add a class header comment
 */
public interface NetworkHelper {

    CategoriesResponse requestCategories();

    VenuesResponse getVenuesForCategory(String category);
}
