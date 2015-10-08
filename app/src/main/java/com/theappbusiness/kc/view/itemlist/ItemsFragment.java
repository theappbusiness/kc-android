package com.theappbusiness.kc.view.itemlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewAnimator;

import com.theappbusiness.kc.ItemDetailActivity;
import com.theappbusiness.kc.R;
import com.theappbusiness.kc.data.Database;
import com.theappbusiness.kc.io.DataLoaderHelper;
import com.theappbusiness.kc.io.KcObservables;
import com.theappbusiness.kc.model.Venue;
import com.theappbusiness.kc.service.BackendOperations;
import com.theappbusiness.kc.utils.IntentExtras;
import com.theappbusiness.kc.view.BaseFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import retrofit.RestAdapter;
import rx.Observable;
import rx.schedulers.Schedulers;


public class ItemsFragment extends BaseFragment implements DataLoaderHelper.ContentFlow<List<Venue>> {
    private static final int CONTENT_VIEW_INDEX = 1;
    private static final String CATEGORY_KEY = "category";

    @Inject
    BackendOperations mBackendOperations;
    @Inject
    RestAdapter mRestAdapter;
    @Inject
    Database mDatabase;
    @Inject
    Picasso mPicasso;

    private ViewAnimator mViewAnimator;
    private ItemsRecyclerAdapter mAdapter;

    /**
     * The recycle view
     */
    private RecyclerView mRecyclerView;
    private DataLoaderHelper mRxFlowHelper;
    private String mCategory;

    public static ItemsFragment newInstance(String category) {
        ItemsFragment fragment = new ItemsFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY_KEY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategory = getArguments().getString(CATEGORY_KEY);
        mAdapter = new ItemsRecyclerAdapter(mPicasso);
        mAdapter.SetOnItemClickListener(onItemClickListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        mRxFlowHelper.onStart();
    }

    @Override
    public void onStop() {
        mRxFlowHelper.onStop();
        super.onStop();
    }

    ItemsRecyclerAdapter.OnItemClickListener onItemClickListener = venue -> {
        Intent intent = new Intent(getActivity(), ItemDetailActivity.class);
        intent.putExtra(IntentExtras.EXTRA_ITEM_KEY, venue);
        startActivity(intent);
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mViewAnimator = (ViewAnimator) inflater.inflate(R.layout.fragment_item_list, container, false);

        mRecyclerView = (RecyclerView) mViewAnimator.findViewById(R.id.item_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        return mViewAnimator;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Fetch remote data
        mRecyclerView.setAdapter(mAdapter);
        mRxFlowHelper = new DataLoaderHelper(this);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showContent(List<Venue> data) {
        if (mViewAnimator.getDisplayedChild() != CONTENT_VIEW_INDEX) {
            mViewAnimator.setDisplayedChild(CONTENT_VIEW_INDEX);
        }
        mAdapter.replace(data);
    }

    @Override
    public void updateContent(List<Venue> data) {
        showContent(data);
    }

    @Override
    public boolean isCacheAvailable() {
        //return mDatabase.isEmpty();
        return false;
    }

    @Override
    public List<Venue> queryCache() {
        return mDatabase.getVenues(mCategory);
    }

    @Override
    public Observable<List<Venue>> queryBackend() {
        return KcObservables.getItemsByCategory(mBackendOperations, mDatabase, mCategory).subscribeOn(Schedulers.io());
    }
}