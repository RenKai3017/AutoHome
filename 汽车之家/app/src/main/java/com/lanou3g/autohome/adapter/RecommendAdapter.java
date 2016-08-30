package com.lanou3g.autohome.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lanou3g.autohome.homereuse.HomeReuseFragment;

import java.util.List;

/**
 * Created by dllo on 16/8/2.
 */
public class RecommendAdapter extends FragmentPagerAdapter {
    private List<Fragment>fragmentList;

    public RecommendAdapter(FragmentManager fm) {
        super(fm);
    }



    @Override
    public Fragment getItem(int position) {
        return HomeReuseFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }
    private String[] titles = {"最新", "快报","新闻", "评测", "导购", "行情", "用车",
                        "技术", "文化", "改装", "游记"};
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
