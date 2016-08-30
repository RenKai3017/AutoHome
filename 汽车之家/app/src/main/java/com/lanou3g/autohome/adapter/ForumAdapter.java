package com.lanou3g.autohome.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/8/2.
 */
public class ForumAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    public ForumAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 :fragmentList.size();
    }
    private String[] titles = {"精选推荐", "热帖", "常用论坛"};

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
