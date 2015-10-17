package com.theappbusiness.kc.view.categories;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewAnimator;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.theappbusiness.kc.KcApp;
import com.theappbusiness.kc.R;
import com.theappbusiness.kc.data.Database;
import com.theappbusiness.kc.di.components.CategoriesComponent;
import com.theappbusiness.kc.di.modules.CategoriesModule;
import com.theappbusiness.kc.io.DataLoaderHelper;
import com.theappbusiness.kc.io.KcObservables;
import com.theappbusiness.kc.model.Category;
import com.theappbusiness.kc.service.BackendOperations;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by jamesscott on 02/03/15.
 */
public class CategoriesActivity extends AppCompatActivity implements DataLoaderHelper.ContentFlow<List<Category>> {
    private static final int CONTENT_VIEW_INDEX = 1;


    private CategoriesComponent mComponent;
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
        setContentView(R.layout.activity_categories);

        mComponent = KcApp.get().getComponent().plus(new CategoriesModule());
        mComponent.inject(this);

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

                mPicasso.with(mCategoryImage.getContext()).load(mData.get(position).getLogoUrl()).into(mCategoryImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        mCategoryImage.startAnimation(AnimationUtils.loadAnimation(CategoriesActivity.this, R.anim.abc_fade_in));
                    }

                    @Override
                    public void onError() {
                        mCategoryImage.setImageDrawable(getResources().getDrawable(R.drawable.image_not_found));
                        mCategoryImage.startAnimation(AnimationUtils.loadAnimation(CategoriesActivity.this, R.anim.abc_fade_in));
                    }
                });

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
