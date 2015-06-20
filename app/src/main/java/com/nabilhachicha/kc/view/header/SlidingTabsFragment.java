package com.nabilhachicha.kc.view.header;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nabilhachicha.kc.R;
import com.nabilhachicha.kc.data.Database;
import com.nabilhachicha.kc.io.DataLoaderHelper;
import com.nabilhachicha.kc.io.KcObservables;
import com.nabilhachicha.kc.model.Category;
import com.nabilhachicha.kc.service.BackendOperations;
import com.nabilhachicha.kc.view.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by jamesscott on 02/03/15.
 */
public class SlidingTabsFragment extends BaseFragment implements DataLoaderHelper.ContentFlow<List<Category>> {
    @Inject
    BackendOperations mBackendOperations;

    @Inject
    Database mDatabase;

    private Toolbar mToolbar;

    private TabLayout mSlidingTabLayout;

    private ViewPager mViewPager;
    private CategoriesPagerAdapter mAdapter;
    private DataLoaderHelper mRxFlowHelper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxFlowHelper = new DataLoaderHelper(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle("App Name");

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mAdapter = new CategoriesPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        mSlidingTabLayout = (TabLayout) view.findViewById(R.id.tabs);
        mSlidingTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mSlidingTabLayout.setupWithViewPager(mViewPager);

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


    @Override
    public void showError() {

    }

    @Override
    public void showContent(List<Category> data) {
        mAdapter.setCategories(data);
        mSlidingTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void updateContent(List<Category> data) {

    }

    @Override
    public boolean isCacheAvailable() {
        return false;
    }

    @Override
    public List<Category> queryCache() {
        return null;
    }

    @Override
    public Observable<List<Category>> queryBackend() {
        return KcObservables.getCategories(mBackendOperations, mDatabase);
    }

}
