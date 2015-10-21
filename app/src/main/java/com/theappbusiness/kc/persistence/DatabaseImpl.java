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

import com.snappydb.DB;
import com.snappydb.SnappyDB;
import com.snappydb.SnappydbException;
import com.theappbusiness.kc.model.Category;
import com.theappbusiness.kc.model.Venue;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public final class DatabaseImpl implements Database {
    private static final String DATABASE_NAME = "kc";

    private DB mSnappyDb;

    @Inject
    public DatabaseImpl(SnappyDB.Builder snappyDbBuilder) {
        try {
            mSnappyDb = snappyDbBuilder
                    .name(DATABASE_NAME)
                    .build();
        } catch (SnappydbException e) {
            Timber.e(e, "Error creating db");
        }
    }

    @Override
    public Venue saveVenue(Venue venue) {
        String key = "tag:" + venue.getCategoryId() + ":poi:" + venue.getId();
        try {
            mSnappyDb.put(key, venue);

        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return venue;
    }

    @Override
    public List<Venue> getVenues(String category) {
        synchronized (mSnappyDb) {
            List<Venue> venues = new ArrayList<>();

            try {
                for (String key : mSnappyDb.findKeys("tag:" + category + ":poi:")) {
                    venues.add(mSnappyDb.get(key, Venue.class));
                }
            } catch (SnappydbException e) {
                e.printStackTrace();
            }

            return venues;

        }
    }

    @Override
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();

        try {
            for (String key : mSnappyDb.findKeys("category:")) {
                categories.add(mSnappyDb.get(key, Category.class));
            }
        } catch (SnappydbException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public Category saveCategory(Category category) {
        String key = "category:" + category.getId();
        try {
            // Save to database
            mSnappyDb.put(key, category);

        } catch (SnappydbException e) {
            e.printStackTrace();//TODO handle Exception elegantly (throw Observable.error)
        }
        return category;//for chaining operators
    }

    @Override
    public boolean isEmpty() {
        synchronized (mSnappyDb) {
            try {
                int nbCat = mSnappyDb.countKeys("category:");
                return nbCat < 1;
            } catch (SnappydbException e) {
                e.printStackTrace();
                throw new IllegalStateException(e);
            }
        }
    }

    @Override
    public void deleteCategories() {
        try {
            for (String key : mSnappyDb.findKeys("category:")) {
                mSnappyDb.del(key);
            }
        } catch (SnappydbException e) {
            e.printStackTrace();//TODO handle Exception elegantly (throw Observable.error)
        }
    }

    @Override
    public void deleteVenues() {
        try {
            for (String key : mSnappyDb.findKeys("tag:")) {
                mSnappyDb.del(key);
            }
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

}
