package com.theappbusiness.kc.controllers.venues;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theappbusiness.kc.controllers.categories.CategoriesActivity;
import com.theappbusiness.kc.controllers.venue.VenueActivity;
import com.theappbusiness.kc.di.components.VenuesComponent;
import com.theappbusiness.kc.di.modules.venues.VenuesModule;
import com.theappbusiness.kc.model.Venue;
import com.theappbusiness.kc.network.venues.VenuesNetworkManager;
import com.theappbusiness.kc.view.venues.VenuesPresentation;

import java.util.List;

import javax.inject.Inject;

public class VenuesFragment extends Fragment implements VenuesController {
    private static final String CATEGORY_KEY = "category";

    private VenuesComponent mComponent;

    @Inject
    VenuesPresentation mPresentation;
    @Inject
    VenuesNetworkManager mNetworkManager;

    public static VenuesFragment newInstance(String category) {
        VenuesFragment fragment = new VenuesFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY_KEY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String categoryId = getArguments().getString(CATEGORY_KEY);

        mComponent = ((CategoriesActivity) getActivity())
                .getComponent()
                .plus(new VenuesModule(this, categoryId));
        mComponent.inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(mPresentation.getLayoutResource(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresentation.init(view);
    }

    @Override
    public void onStart() {
        super.onStart();
        mNetworkManager.requestVenues();
    }

    @Override
    public void onPause() {
        super.onPause();
        mNetworkManager.cancelRequest();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mComponent = null;
    }

    @Override
    public void onVenusLoaded(List<Venue> venues) {
        mPresentation.showVenues(venues);
    }

    @Override
    public void onVenueClicked(Venue venue) {
        VenueActivity.launch(getActivity(), venue);
    }
}
