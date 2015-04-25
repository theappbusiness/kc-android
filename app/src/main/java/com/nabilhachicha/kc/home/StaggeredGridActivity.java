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

package com.nabilhachicha.kc.home;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.etsy.android.grid.StaggeredGridView;
import com.nabilhachicha.kc.BaseActivity;
import com.nabilhachicha.kc.data.Database;
import com.nabilhachicha.kc.io.KcObservables;
import com.nabilhachicha.kc.model.Category;
import com.nabilhachicha.kc.service.BackendOperations;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by Nabil Hachicha on 11/12/14.
 */
public class StaggeredGridActivity extends BaseActivity implements StaggeredGridView.OnItemClickListener, DataLoaderHelper.ContentFlow<List<Category>> {
    @Inject
    BackendOperations mBackendOperations;

    @Inject
    Database mDatabase;

    @Inject
    Picasso mPicasso;

    private DataLoaderHelper mRxFlowHelper;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRxFlowHelper = new DataLoaderHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRxFlowHelper.onStart();
    }

    @Override
    protected void onStop() {
        mRxFlowHelper.onStop();
        super.onStop();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Category category = mAdapter.getItem(position);
//        Intent intent = new Intent(this, ItemsActivity.class);
//        intent.putExtra("category", category);
//        startActivity(intent);
    }

    // Rx Flow Helper
    @Override
    public void showError() {//TODO rename to onSomething
        // Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showContent(List<Category> data) {
//        mAdapter.replace(mDatabase.getCategories());
    }

    @Override
    public void updateContent(List<Category> data) {
        showContent(data);
    }

    @Override
    public boolean isCacheAvailable() {
        return mDatabase.isEmpty();
    }

    @Override
    public List<Category> queryCache() {
        return mDatabase.getCategories();
    }

    @Override
    public Observable<List<Category>> queryBackend() {
        return KcObservables.getCategories(mBackendOperations, mDatabase).subscribeOn(Schedulers.io());
    }


}
