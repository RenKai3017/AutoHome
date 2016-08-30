package com.lanou3g.autohome.adapter.my;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/8/17.
 */
public class CollectionTabAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    public CollectionTabAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    private String[] titles = {"文章收藏","车系收藏","车型收藏","口碑收藏","说客收藏","视频收藏","论坛收藏","帖子收藏","收藏"};

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
