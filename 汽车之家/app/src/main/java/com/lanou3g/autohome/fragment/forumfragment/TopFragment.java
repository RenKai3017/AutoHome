package com.lanou3g.autohome.fragment.forumfragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.adapter.forum.TopAdapter;
import com.lanou3g.autohome.base.BaseFragment;
import com.lanou3g.autohome.bean.forum.TopBean;
import com.lanou3g.autohome.tool.DividerItemDecoration;
import com.lanou3g.autohome.tool.GsonRequest;
import com.lanou3g.autohome.tool.InterfaceValues;
import com.lanou3g.autohome.tool.SingleQueue;

/**
 * Created by dllo on 16/8/2.
 */
public class TopFragment extends BaseFragment {
    private RecyclerView rvTop;
    private TopAdapter topAdapter;
    @Override
    protected int getLayout() {
        return R.layout.fragment_article;
    }

    @Override
    protected void initView(View view) {
        rvTop = findView(R.id.rv_top);
        rvTop.setLayoutManager(new LinearLayoutManager(context));
        rvTop.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    protected void initData() {
        topAdapter = new TopAdapter(context);
        GsonRequest<TopBean> gsonRequest = new GsonRequest<>(InterfaceValues.TOP_POSTS_URL, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, new Listener<TopBean>() {
            @Override
            public void onResponse(TopBean response) {
                topAdapter.setTopBean(response);
                rvTop.setAdapter(topAdapter);
            }
        }, TopBean.class);
        SingleQueue.getSingleQueue(context).getRequestQueue().add(gsonRequest);
    }
}
