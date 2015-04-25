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

package com.nabilhachicha.kc.data;

import android.app.Application;

import com.nabilhachicha.kc.model.Category;
import com.nabilhachicha.kc.model.Venue;
import com.snappydb.DB;
import com.snappydb.SnappyDB;
import com.snappydb.SnappydbException;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Nabil Hachicha on 05/12/14.
 */
@SuppressWarnings("SynchronizeOnNonFinalField")
public final class DatabaseImpl implements Database {
    private DB snappydb;

    public DatabaseImpl(Application application) {
        try {
            snappydb = new SnappyDB.Builder(application)
                    .name("kc")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Venue saveVenue(Venue venue) {
        synchronized (snappydb) {
            String key = "tag:" + venue.getCategoryId() + ":poi:" + venue.getId();
            try {
                snappydb.put(key, venue);

            } catch (SnappydbException e) {
                e.printStackTrace();
                Observable.error(e);
            }
            return venue;
        }
    }

    @Override
    public List<Venue> getVenues(String category) {
        synchronized (snappydb) {
            ArrayList<Venue> venues = new ArrayList<>();

            try {
                for (String key : snappydb.findKeys("tag:" + category + ":poi:")) {
                    venues.add(snappydb.get(key, Venue.class));
                }
            } catch (SnappydbException e) {
                e.printStackTrace();
            }

            return venues;

        }
    }

    @Override
    public List<Category> getCategories() {
        synchronized (snappydb) {
            ArrayList<Category> categories = new ArrayList<>();

            try {
                for (String key : snappydb.findKeys("category:")) {
                    categories.add(snappydb.get(key, Category.class));
                }
            } catch (SnappydbException e) {
                e.printStackTrace();
            }

            return categories;

        }
    }

    @Override
    public Category saveCategory(Category category) {
        synchronized (snappydb) {
            String key = "category:" + category.getId();
            try {
                // Save to database
                snappydb.put(key, category);

            } catch (SnappydbException e) {
                e.printStackTrace();//TODO handle Exception elegantly (throw Observable.error)
            }
            return category;//for chaining operators
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (snappydb) {
            try {
                int nbCat = snappydb.countKeys("category:");
                return nbCat < 1;
            } catch (SnappydbException e) {
                e.printStackTrace();
                throw new IllegalStateException(e);
            }
        }
    }

    @Override
    public void deleteCategories() {
        synchronized (snappydb) {
            try {
                for (String key : snappydb.findKeys("category:")) {
                    snappydb.del(key);
                }
            } catch (SnappydbException e) {
                e.printStackTrace();//TODO handle Exception elegantly (throw Observable.error)
            }
        }
    }

    @Override
    public void deleteVenues() {
        synchronized (snappydb) {
            try {
                for (String key : snappydb.findKeys("tag:")) {
                    snappydb.del(key);
                }
            } catch (SnappydbException e) {
                e.printStackTrace();
            }
        }
    }

}
