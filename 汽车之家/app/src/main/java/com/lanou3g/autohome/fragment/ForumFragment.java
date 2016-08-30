package com.lanou3g.autohome.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lanou3g.autohome.R;
import com.lanou3g.autohome.adapter.ForumAdapter;
import com.lanou3g.autohome.base.BaseFragment;
import com.lanou3g.autohome.fragment.forumfragment.TopFragment;
import com.lanou3g.autohome.fragment.forumfragment.CommonForumFragment;
import com.lanou3g.autohome.fragment.forumfragment.SelectionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/8/2.
 */
public class ForumFragment extends BaseFragment {
    private TabLayout forumTab;
    private ViewPager forumVp;
    private List<Fragment>fragmentList;
    private ForumAdapter mForumAdapter;
    @Override
    protected int getLayout() {
        return R.layout.fragment_forum;
    }

    @Override
    protected void initView(View view) {
        forumTab = findView(R.id.forum_tab);
        forumVp = findView(R.id.forum_vp);
    }

    @Override
    protected void initData() {
        initFragment();
        mForumAdapter = new ForumAdapter(getChildFragmentManager(),fragmentList);
        forumVp.setAdapter(mForumAdapter);
        forumTab.setupWithViewPager(forumVp);

    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new SelectionFragment());
        fragmentList.add(new TopFragment());
        fragmentList.add(new CommonForumFragment());
    }
}
