package com.lanou3g.autohome.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lanou3g.autohome.R;
import com.lanou3g.autohome.adapter.findcar.FindAutoAdapter;
import com.lanou3g.autohome.base.BaseFragment;
import com.lanou3g.autohome.fragment.findautofragment.BrandFragment;
import com.lanou3g.autohome.fragment.findautofragment.CutPriceFragment;
import com.lanou3g.autohome.fragment.findautofragment.FilterFragment;
import com.lanou3g.autohome.fragment.findautofragment.OldCarFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/8/2.
 */
public class FindAutoFragment extends BaseFragment {
    private TabLayout findAutoTab;
    private ViewPager findAutoVp;
    private List<Fragment>fragmentList;
    private FindAutoAdapter mFindAutoAdapter;
    @Override
    protected int getLayout() {
        return R.layout.fragment_find_auto;
    }

    @Override
    protected void initView(View view) {
        findAutoTab = findView(R.id.find_auto_tab);
        findAutoVp = findView(R.id.find_auto_vp);
    }

    @Override
    protected void initData() {
        initFragment();
        mFindAutoAdapter = new FindAutoAdapter(getChildFragmentManager());
        mFindAutoAdapter.setFragmentList(fragmentList);
        findAutoVp.setAdapter(mFindAutoAdapter);
        findAutoTab.setupWithViewPager(findAutoVp);

    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new BrandFragment());
        fragmentList.add(new FilterFragment());
        fragmentList.add(new CutPriceFragment());
        fragmentList.add(new OldCarFragment());

    }
}
