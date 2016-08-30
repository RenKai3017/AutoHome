package com.lanou3g.autohome.fragment.forumfragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lanou3g.autohome.R;
import com.lanou3g.autohome.adapter.ReuseAdapter;
import com.lanou3g.autohome.adapter.SelectionAdapter;
import com.lanou3g.autohome.base.BaseFragment;

/**
 * Created by dllo on 16/8/2.
 */
public class SelectionFragment extends BaseFragment {
    private TabLayout selectTab;
    private ViewPager selectVp;
    private SelectionAdapter selectionAdapter;
    @Override
    protected int getLayout() {
        return R.layout.fragment_selection;
    }

    @Override
    protected void initView(View view) {
        selectTab = findView(R.id.select_tab);
        selectVp = findView(R.id.select_vp);
    }

    @Override
    protected void initData() {
        selectionAdapter = new SelectionAdapter(getChildFragmentManager());
        selectVp.setAdapter(selectionAdapter);
        selectTab.setupWithViewPager(selectVp);
    }
}
