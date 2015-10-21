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

import com.theappbusiness.kc.data.Database;
import com.theappbusiness.kc.model.CategoriesResponse;
import com.theappbusiness.kc.model.Category;
import com.theappbusiness.kc.model.Venue;
import com.theappbusiness.kc.model.VenuesResponse;
import com.theappbusiness.kc.service.BackendOperations;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Nabil Hachicha on 10/12/14.
 */
public class KcObservables {

    public static Observable<List<Category>> getCategories(final BackendOperations operations, final Database database) {
        return operations.getCategories().flatMap(categoriesResponse -> Observable.just(categoriesResponse.get()));
    }


    public static Observable<List<Venue>> getItemsByCategory(final BackendOperations operations, final Database database, String category) {
        return operations.getVenuesByCategory(category).flatMap(venuesResponse -> Observable.just(venuesResponse.getVenues()));
    }
}
