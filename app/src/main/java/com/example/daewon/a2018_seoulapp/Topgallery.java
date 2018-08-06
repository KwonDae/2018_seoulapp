package com.example.daewon.a2018_seoulapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

public class Topgallery extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topgallery);

        TestPagerAdapter mTestPagerAdapter = new TestPagerAdapter(
                getSupportFragmentManager()
        );
        ViewPager mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(mTestPagerAdapter);

        TabLayout mTab = findViewById(R.id.tabs);
        mTab.setupWithViewPager(mViewPager);
    }
}
