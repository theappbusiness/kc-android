package com.nabilhachicha.kc.view.categories;


import com.nabilhachicha.kc.R;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by swav on 08/10/15.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = CustomBuildConfig.class, sdk = 21)
public class CategoriesActivityTest {


    @Test
    public void failme() {
        CategoriesActivity activity = Robolectric.setupActivity(CategoriesActivity.class);
        assertThat(activity).isNotNull();
        assertThat(activity.findViewById(R.id.viewpager)).isNotNull();


    }
}
