package com.theappbusiness.kc.view.venue;

import android.app.FragmentManager;
import android.content.res.Resources;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.theappbusiness.kc.R;
import com.theappbusiness.kc.controllers.venue.VenueController;
import com.theappbusiness.kc.io.ImageHelper;
import com.theappbusiness.kc.model.Venue;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * TODO Add a class header comment
 */
public class VenuePresentationImpl implements VenuePresentation, OnMapReadyCallback {
    private static final float MAP_ASPECT_RATIO = 16f / 9f;
    private static final int MAP_ZOOM = 15;

    private final Venue mVenue;
    private final VenueController mController;
    private final ImageHelper mImageHelper;
    private final FragmentManager mFragmentManager;
    private final Resources mResources;


    @Inject
    public VenuePresentationImpl(Venue venue, VenueController controller, ImageHelper imageHelper,
                                 FragmentManager fragmentManager, Resources resources) {
        mVenue = venue;
        mController = controller;
        mImageHelper = imageHelper;
        mFragmentManager = fragmentManager;
        mResources = resources;
    }


    @Override
    public int getLayoutResource() {
        return R.layout.activity_venue;
    }

    @Override
    public void init(View view) {
        ButterKnife.bind(this, view);

        Toolbar toolbar = ButterKnife.findById(view, R.id.toolbar);
        toolbar.setNavigationIcon(mResources.getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha));
        toolbar.setNavigationOnClickListener(v -> mController.onBackPressed());

        int screenWidth = mResources.getDisplayMetrics().widthPixels;

        ImageView imgVenue = ButterKnife.findById(view, R.id.venueImg);
        mImageHelper.load(mVenue.getImageUrl())
                .resize(screenWidth, screenWidth)
                .centerInside()
                .into(imgVenue);


        TextView txtCommentary = ButterKnife.findById(view, R.id.textCommentary);
        TextView txtOpeningTimes = ButterKnife.findById(view, R.id.textOpeningTimes);
        TextView txtPhone = ButterKnife.findById(view, R.id.textPhone);
        TextView txtWebsite = ButterKnife.findById(view, R.id.textWebsite);
        TextView txtAddress = ButterKnife.findById(view, R.id.textAddress);

        txtCommentary.setText(mVenue.getCommentary());
        txtOpeningTimes.setText(mVenue.getOpeningTimes());
        txtPhone.setText(mVenue.getPhoneNumber());
        txtWebsite.setText(mVenue.getWebsite());
        txtAddress.setText(mVenue.getAddress());


        CollapsingToolbarLayout collapsingToolbarLayout = ButterKnife.findById(view, R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(mVenue.getName());
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.item_details_expanded_toolbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.item_details_collapsed_toolbar);

        View mapView = ButterKnife.findById(view, R.id.map);
        mapView.getLayoutParams().height = (int) (screenWidth / MAP_ASPECT_RATIO);
        MapFragment mapFragment = (MapFragment) mFragmentManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setOnMapClickListener(latLng -> {
            // ignore map clicks
        });

        // remove map buttons
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setMapToolbarEnabled(false);

        LatLng location = new LatLng(mVenue.getLattitude(), mVenue.getLongitude());
        googleMap.addMarker(new MarkerOptions()
                .position(location)
                .title(mVenue.getName()));

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, MAP_ZOOM));
    }

    @OnClick(R.id.textPhone)
    public void onPhoneClicked() {
        mController.onCallClicked(mVenue.getPhoneNumber());
    }

    @OnClick(R.id.textWebsite)
    public void onWebsiteClicked() {
        mController.onWebsiteClicked(mVenue.getWebsite());
    }

    @OnClick(R.id.fab_activity_action_button)
    public void onDirectionsClicked() {
        mController.onGetDirectionsClicked(mVenue);
    }
}
