package com.nabilhachicha.kc.home.jamesclasses.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.nabilhachicha.kc.R;
import com.nabilhachicha.kc.home.jamesclasses.deleteme.RecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by jamesscott on 02/03/15.
 */
public class SlidingTabsFragment extends Fragment {

    private Toolbar mToolbar;
    private ImageButton mFabButton;

    private SlidingTabLayout mSlidingTabLayout;

    private ViewPager mViewPager;

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

//        mFabButton = (ImageButton) view.findViewById(R.id.fabButton);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SamplePagerAdapter());

        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
    }


    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Category " + (position + 1);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.deleteme_pageritem, container, false);

            ArrayList<String> data = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                data.add(Integer.toString(i));
            }

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(data);
            recyclerView.setAdapter(recyclerAdapter);
//            recyclerView.addOnScrollListener(new HidingScrollListener() {
//                @Override
//                public void onHide() {
//                    hideViews();
//                }
//
//                @Override
//                public void onShow() {
//                    showViews();
//                }
//            });

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
