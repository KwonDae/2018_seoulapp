package com.example.daewon.a2018_seoulapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.daewon.a2018_seoulapp.Fragment.PageOneFragment;
import com.example.daewon.a2018_seoulapp.Fragment.PageThreeFragment;
import com.example.daewon.a2018_seoulapp.Fragment.PageTwoFragment;

public class TestPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_NUMBER = 3;

    public TestPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PageOneFragment.newInstance();
            case 1:
                return PageTwoFragment.newInstance();
            case 2:
                return PageThreeFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Top 5";
            case 1:
                return "지도로 보기";
            case 2:
                return "나의 갤러리";
            default:
                return null;
        }
    }


}
