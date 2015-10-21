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

package com.theappbusiness.kc.controllers.venue;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.theappbusiness.kc.KcApp;
import com.theappbusiness.kc.di.modules.venue.VenueModule;
import com.theappbusiness.kc.model.Venue;
import com.theappbusiness.kc.utils.MapUtils;
import com.theappbusiness.kc.view.venue.VenuePresentation;

import javax.inject.Inject;


public class VenueActivity extends AppCompatActivity implements VenueController {
    private static final String EXTRA_VENUE = "venue";
    private static final String ACTION_DIAL_URI_SCHEME = "tel:";

    public static void launch(Activity activity, Venue venue) {
        Intent intent = new Intent(activity, VenueActivity.class);
        intent.putExtra(EXTRA_VENUE, venue);
        activity.startActivity(intent);
    }

    @Inject
    VenuePresentation mPresentation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Venue venue = (Venue) getIntent().getSerializableExtra(EXTRA_VENUE);
        KcApp.get().getComponent()
                .plus(new VenueModule(this, venue))
                .inject(this);
        setContentView(mPresentation.getLayoutResource());
        mPresentation.init(findViewById(android.R.id.content));
    }

    @Override
    public void onGetDirectionsClicked(Venue venue) {
        MapUtils.startMapIntent(venue, this);
    }

    @Override
    public void onCallClicked(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(ACTION_DIAL_URI_SCHEME + phone));
        startActivity(intent);
    }

    @Override
    public void onWebsiteClicked(String websiteUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(websiteUrl));
        startActivity(intent);
    }
}
