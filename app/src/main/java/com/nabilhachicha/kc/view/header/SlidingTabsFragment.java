package com.nabilhachicha.kc.view.header;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.nabilhachicha.kc.R;
import com.nabilhachicha.kc.data.Database;
import com.nabilhachicha.kc.io.DataLoaderHelper;
import com.nabilhachicha.kc.io.KcObservables;
import com.nabilhachicha.kc.model.Category;
import com.nabilhachicha.kc.service.BackendOperations;
import com.nabilhachicha.kc.view.BaseFragment;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

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

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mAdapter = new CategoriesPagerAdapter(getActivity().getSupportFragmentManager());
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

                mPicasso.with(mCategoryImage.getContext()).load(mData.get(position).getLogoUrl()).into(mCategoryImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        mCategoryImage.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.abc_fade_in));
                    }

                    @Override
                    public void onError() {
                        mCategoryImage.setImageDrawable(getResources().getDrawable(R.drawable.image_not_found));
                        mCategoryImage.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.abc_fade_in));
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
        this.mData = data;
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
