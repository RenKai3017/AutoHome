package com.lanou3g.autohome.adapter.findcar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/8/3.
 */
public class FindAutoAdapter extends FragmentPagerAdapter {
    private List<Fragment>fragmentList;
    public FindAutoAdapter(FragmentManager fm) {
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
        return fragmentList == null ? 0 : fragmentList.size();
    }
    private String[] titles = {"品牌", "筛选", "降价", "找二手车"};

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
