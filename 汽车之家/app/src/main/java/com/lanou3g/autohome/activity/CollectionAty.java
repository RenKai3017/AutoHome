package com.lanou3g.autohome.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

import com.lanou3g.autohome.R;
import com.lanou3g.autohome.adapter.my.CollectionTabAdapter;
import com.lanou3g.autohome.base.BaseActivity;
import com.lanou3g.autohome.fragment.my.CollectionArticleFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/8/17.
 */
public class CollectionAty extends BaseActivity {
    private TabLayout collectionTab;
    private ViewPager collectionVp;
    private List<Fragment>fragmentList;
    private CollectionTabAdapter tabAdapter;
    @Override
    protected int getLayout() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initView() {
        collectionTab = findView(R.id.collection_tab);
        collectionVp = findView(R.id.collection_vp);
        findView(R.id.collection_back_tv).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        initFragment();
        tabAdapter = new CollectionTabAdapter(getSupportFragmentManager());
        tabAdapter.setFragmentList(fragmentList);
        collectionVp.setAdapter(tabAdapter);
        collectionTab.setupWithViewPager(collectionVp);
        collectionTab.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new CollectionArticleFragment());
    }


}
