package com.theappbusiness.kc.view.categories;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewAnimator;

import com.theappbusiness.kc.R;
import com.theappbusiness.kc.io.ImageHelper;
import com.theappbusiness.kc.model.Category;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by swav on 08/10/15.
 */
public class CategoriesPresentationImpl implements CategoriesPresentation, ViewPager.OnPageChangeListener {

    private static final int CONTENT_VIEW_INDEX = 1;

    @Bind(R.id.main_content_view_animator)
    ViewAnimator mViewAnimator;

    @Bind(R.id.tabs)
    TabLayout mSlidingTabLayout;

    @Bind(R.id.viewpager)
    ViewPager mViewPager;

    @Bind(R.id.backdrop)
    ImageView mCategoryImage;


    private final CategoriesPagerAdapter mAdapter;
    private final ImageHelper mImageHelper;
    private final Resources mResources;


    @Inject
    public CategoriesPresentationImpl(CategoriesPagerAdapter adapter, ImageHelper imageHelper,
                                      Resources resources) {
        mAdapter = adapter;
        mImageHelper = imageHelper;
        mResources = resources;
    }

    @Override
    public void init(View view) {
        ButterKnife.bind(this, view)
        ;
        mViewPager.setAdapter(mAdapter);
        mCategoryImage = (ImageView) view.findViewById(R.id.backdrop);

        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        mSlidingTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mSlidingTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(this);
    }


    @Override
    public void showCategories(List<Category> data) {
        if (mViewAnimator.getDisplayedChild() != CONTENT_VIEW_INDEX) {
            mViewAnimator.setDisplayedChild(CONTENT_VIEW_INDEX);
        }

        mAdapter.setCategories(data);
        mSlidingTabLayout.setupWithViewPager(mViewPager);
        onPageSelected(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mImageHelper.load(mAdapter.getCategory(position).getLogoUrl())
                .into(mCategoryImage);

        /*mPicasso.with(mCategoryImage.getContext()).load(mAdapter.getCategory(position)
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
                        });*/
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
