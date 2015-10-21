package com.theappbusiness.kc.view.venues;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ViewSwitcher;

import com.theappbusiness.kc.R;
import com.theappbusiness.kc.model.Venue;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * TODO Add a class header comment
 */
public class VenuesPresentationImpl implements VenuesPresentation {
    private static final int CONTENT_VIEW_INDEX = 1;

    @Bind(R.id.list_venues)
    RecyclerView mVenuesList;

    private ViewSwitcher mViewSwitcher;

    private final VenuesAdapter mAdapter;
    private final RecyclerView.LayoutManager mLayoutManager;

    @Inject
    public VenuesPresentationImpl(VenuesAdapter adapter, RecyclerView.LayoutManager layoutManager) {
        mAdapter = adapter;
        mLayoutManager = layoutManager;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_venues;
    }

    @Override
    public void init(View view) {
        ButterKnife.bind(this, view);
        mViewSwitcher = (ViewSwitcher) view;
        mVenuesList.setLayoutManager(mLayoutManager);
        mVenuesList.setAdapter(mAdapter);
    }

    @Override
    public void showVenues(List<Venue> venues) {
        if (mViewSwitcher.getDisplayedChild() != CONTENT_VIEW_INDEX) {
            mViewSwitcher.setDisplayedChild(CONTENT_VIEW_INDEX);
        }

        mAdapter.setVenues(venues);
    }
}
