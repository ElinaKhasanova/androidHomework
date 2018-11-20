package com.example.elina.bottomnavigation;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    private Context context = null;

    public PageAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return (PageFragment.newInstance(position));
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public String getPageTitle(int position) {
        return (PageFragment.getTitle(context, position));
    }
}
