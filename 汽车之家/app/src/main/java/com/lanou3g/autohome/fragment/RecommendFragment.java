package com.lanou3g.autohome.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lanou3g.autohome.R;
import com.lanou3g.autohome.adapter.RecommendAdapter;
import com.lanou3g.autohome.base.BaseFragment;

/**
 * Created by dllo on 16/8/1.
 */
public class RecommendFragment extends BaseFragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private RecommendAdapter mRecommendAdapter;
    @Override
    protected int getLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView(View view) {
        mTabLayout = findView(R.id.recommend_tab);
        mViewPager = findView(R.id.recommend_vp);
    }

    @Override
    protected void initData() {
        mRecommendAdapter = new RecommendAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mRecommendAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


}
