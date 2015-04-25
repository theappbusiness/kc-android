package com.nabilhachicha.kc.home.jamesclasses.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.nabilhachicha.kc.R;
import com.nabilhachicha.kc.items.itemlist.ItemsFragment;

/**
 * Created by jamesscott on 02/03/15.
 */
public class SlidingTabsFragment extends Fragment {

    private Toolbar mToolbar;
    private ImageButton mFabButton;

    private LinearLayout header;

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

        header = (LinearLayout) view.findViewById(R.id.header_toolbar);

//        mFabButton = (ImageButton) view.findViewById(R.id.fabButton);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SamplePagerAdapter(getActivity().getSupportFragmentManager()));

        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
    }


    /**
     * Pager item
     */
    class SamplePagerAdapter extends FragmentStatePagerAdapter {

        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Category " + (position + 1);
        }

        @Override
        public Fragment getItem(int i) {
            return ItemsFragment.newInstance("mock");
        }


        public int getColorWithAlpha(float alpha, int baseColor) {
            int a = Math.min(255, Math.max(0, (int) (alpha * 255))) << 24;
            int rgb = 0x00ffffff & baseColor;
            return a + rgb;
        }

    }

}
