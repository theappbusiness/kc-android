package com.nabilhachicha.kc.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nabilhachicha.kc.R;
import com.nabilhachicha.kc.home.view.SlidingTabsFragment;

public class KingsCrossActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kingscross);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new SlidingTabsFragment())
                    .commit();
        }
    }

}
