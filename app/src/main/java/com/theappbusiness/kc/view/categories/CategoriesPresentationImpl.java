package com.theappbusiness.kc.view.categories;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewAnimator;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.theappbusiness.kc.R;
import com.theappbusiness.kc.di.scopes.PerApplication;
import com.theappbusiness.kc.model.Category;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by swav on 08/10/15.
 */
public class CategoriesPresentationImpl implements CategoriesPresentation{

    private static final int CONTENT_VIEW_INDEX = 1;


    private ViewAnimator mViewAnimator;


    private TabLayout mSlidingTabLayout;

    private ViewPager mViewPager;

    @Inject
    CategoriesPagerAdapterImpl mAdapter;

    private ImageView mCategoryImage;

    @Inject
    Picasso mPicasso;

    @Inject
    Resources mResources;

    @Inject
    @PerApplication
    Context mApplicationContext;

    private ViewPager.OnPageChangeListener mPageChangeListener;


    @Inject
    public CategoriesPresentationImpl() {}

    @Override
    public void init(View view) {
        mViewAnimator = (ViewAnimator) view.findViewById(R.id.main_content_view_animator);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(mAdapter);
        mCategoryImage = (ImageView) view.findViewById(R.id.backdrop);

        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        mSlidingTabLayout = (TabLayout) view.findViewById(R.id.tabs);
        mSlidingTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mSlidingTabLayout.setupWithViewPager(mViewPager);
        mPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPicasso.with(mCategoryImage.getContext()).load(mAdapter.getCategory(position)
                        .getLogoUrl()).into
                        (mCategoryImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        mCategoryImage.startAnimation(AnimationUtils.loadAnimation(mApplicationContext, R.anim
                                .abc_fade_in));
                    }

                    @Override
                    public void onError() {
                        mCategoryImage.setImageDrawable(mResources.getDrawable(R.drawable.image_not_found));
                        mCategoryImage.startAnimation(AnimationUtils.loadAnimation(mApplicationContext, R.anim
                                .abc_fade_in));
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
    public void showContent(List<Category> data) {
        if (mViewAnimator.getDisplayedChild() != CONTENT_VIEW_INDEX) {
            mViewAnimator.setDisplayedChild(CONTENT_VIEW_INDEX);
        }

        mAdapter.setCategories(data);
        mSlidingTabLayout.setupWithViewPager(mViewPager);
        mPageChangeListener.onPageSelected(0);
    }
}
