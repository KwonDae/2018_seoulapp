package com.example.daewon.a2018_seoulapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

public class Topgallery extends BaseActivity {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

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

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if( 0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
        }
        else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 뒤로가기 누르시면 종료합니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
