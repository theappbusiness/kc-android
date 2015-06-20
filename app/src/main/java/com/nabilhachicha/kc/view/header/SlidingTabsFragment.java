package com.nabilhachicha.kc.view.header;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.ViewAnimator;

import com.nabilhachicha.kc.BaseActivity;
import com.nabilhachicha.kc.R;
import com.nabilhachicha.kc.data.Database;
import com.nabilhachicha.kc.io.DataLoaderHelper;
import com.nabilhachicha.kc.io.KcObservables;
import com.nabilhachicha.kc.model.Category;
import com.nabilhachicha.kc.service.BackendOperations;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by jamesscott on 02/03/15.
 */
public class SlidingTabsFragment extends BaseActivity implements DataLoaderHelper.ContentFlow<List<Category>> {
    private static final int CONTENT_VIEW_INDEX = 1;

    private ViewAnimator mViewAnimator;

    @Inject
    BackendOperations mBackendOperations;

    @Inject
    Database mDatabase;

    private TabLayout mSlidingTabLayout;

    private ViewPager mViewPager;
    private CategoriesPagerAdapter mAdapter;
    private DataLoaderHelper mRxFlowHelper;
    private ImageView mCategoryImage;

    @Inject
    Picasso mPicasso;
    private List<Category> mData;
    private ViewPager.OnPageChangeListener mPageChangeListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewAnimator = (ViewAnimator) findViewById(R.id.main_content_view_animator);
        mRxFlowHelper = new DataLoaderHelper(this);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mAdapter = new CategoriesPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mCategoryImage = (ImageView) findViewById(R.id.backdrop);

        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        mSlidingTabLayout = (TabLayout) findViewById(R.id.tabs);
        mSlidingTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mSlidingTabLayout.setupWithViewPager(mViewPager);
        mPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPicasso.with(mCategoryImage.getContext()).load(mData.get(position).getLogoUrl()).into(mCategoryImage);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
        mViewPager.addOnPageChangeListener(mPageChangeListener);

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
        mData = data;
        if (mViewAnimator.getDisplayedChild() != CONTENT_VIEW_INDEX) {
            mViewAnimator.setDisplayedChild(CONTENT_VIEW_INDEX);
        }

        mAdapter.setCategories(data);
        mSlidingTabLayout.setupWithViewPager(mViewPager);
        mPageChangeListener.onPageSelected(0);
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
