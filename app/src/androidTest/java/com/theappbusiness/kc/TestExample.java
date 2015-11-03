package com.theappbusiness.kc;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.theappbusiness.kc.controllers.categories.CategoriesActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.assertj.android.api.Assertions.*;

/**
 * Created by swav on 03/11/15.
 */
@RunWith(AndroidJUnit4.class)
public class TestExample {

    @Rule
    public ActivityTestRule<CategoriesActivity> activityActivityTestRule = new
            ActivityTestRule<>(CategoriesActivity.class);


    @Before
    public void setUp() {
    }


    @Test
    public void testOne() {
        assertThat(activityActivityTestRule.getActivity()).isNotNull();
    }
}
