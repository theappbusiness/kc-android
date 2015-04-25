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

package com.nabilhachicha.kc.items;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nabilhachicha.kc.BaseActivity;
import com.nabilhachicha.kc.R;
import com.nabilhachicha.kc.model.Venue;
import com.nabilhachicha.kc.utils.MapUtils;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Created by Nabil on 11/12/14.
 */
public class ItemDetailActivity extends BaseActivity implements OnMapReadyCallback {
    private static final float ASPECT_RATIO = 16f / 9f;

    @Inject
    Picasso mPicasso;

    ImageView mItemImg;
    TextView mTextName, mTextDescription, mTextCommentary;
    Venue mVenue;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mVenue = (Venue) getIntent().getSerializableExtra("item");

        setContentView(R.layout.item_details);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha));
        mToolbar.setNavigationOnClickListener(v -> {
            //What to do on back clicked
            finish();
        });

        mItemImg = (ImageView) findViewById(R.id.itemImg);
        mTextName = (TextView) findViewById(R.id.textName);
        mTextDescription = (TextView) findViewById(R.id.textDescription);
        mTextCommentary = (TextView) findViewById(R.id.textCommentary);

        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        mPicasso.load(mVenue.getImageUrl()).resize(screenWidth, screenWidth).centerInside().into(mItemImg);
        mTextName.setText(mVenue.getName());
        mTextDescription.setText(mVenue.getDescription());
        mTextCommentary.setText(mVenue.getCommentary().trim());

        findViewById(R.id.map).getLayoutParams().height = (int) (screenWidth / ASPECT_RATIO);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        Fragment mMapFragment = getFragmentManager().findFragmentById(R.id.map);
//        mMapFragment.setW

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        // disable map click
        googleMap.setOnMapClickListener(latLng -> {
            // ignore
        });

        // remove map buttons
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setMapToolbarEnabled(false);

        LatLng location = new LatLng(mVenue.getLattitude(), mVenue.getLongitude());
        googleMap.addMarker(new MarkerOptions()
                .position(location)
                .title(mVenue.getName()));


        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
    }

    public void openDirections(View view) {
        MapUtils.startMapIntent(mVenue, this);
    }

}
