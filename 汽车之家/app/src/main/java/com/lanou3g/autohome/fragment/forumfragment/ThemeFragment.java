package com.lanou3g.autohome.fragment.forumfragment;

import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.autohome.bean.forum.ThemebbsBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.adapter.forum.ThemeForumAdapter;
import com.lanou3g.autohome.base.BaseFragment;
import com.lanou3g.autohome.tool.AllInterface;
import com.lanou3g.autohome.volleyrequest.VolleySingleQueue;

/**
 * Created by dllo on 16/8/11.
 */
public class ThemeFragment extends BaseFragment {
    private ListView listView;
    private ThemeForumAdapter themeForumAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_theme;
    }

    @Override
    protected void initView(View view) {
        listView = (ListView) getView().findViewById(R.id.theme_lest_view);

    }

    @Override
    protected void initData() {
        themeForumAdapter = new ThemeForumAdapter(context);
        VolleySingleQueue.addRequest(AllInterface.THEME_BBS_URL, ThemebbsBean.class, new Response.Listener<ThemebbsBean>() {
            @Override
            public void onResponse(ThemebbsBean response) {

                themeForumAdapter.setThemeForumBean(response);
                listView.setAdapter(themeForumAdapter);
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.getMessage();
            }
        });


    }
}
