package com.fajaryga.doyog;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPageAdapter extends FragmentPagerAdapter {
    public static int halaman = 2;
    private String judultab[] = new String[]{"Home", "Pencarian"};

    TabPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0){
            fragment = new HomeFragment();
        }
        else if (position == 1){
            fragment = new SearchFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return halaman;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return judultab[position];
    }
}
