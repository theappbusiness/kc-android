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

package com.theappbusiness.kc.persistence;

import com.theappbusiness.kc.model.Category;
import com.theappbusiness.kc.model.Venue;

import java.util.List;

/**
 * Created by Nabil Hachicha on 05/12/14.
 */
public interface Database {

    Venue saveVenue(final Venue venue);

    /**
     * Return all Point Of Interests for the given {@code category}
     *
     * @param category POI's category
     * @return List of POIs belonging to the  {@code category}
     */
    List<Venue> getVenues(final String category);

    void deleteVenues();

    List<Category> getCategories();

    Category saveCategory(final Category category);

    void deleteCategories();

    boolean isEmpty();

}
