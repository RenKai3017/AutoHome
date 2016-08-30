package com.lanou3g.autohome.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.lanou3g.autohome.bean.ReuseBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.activity.detail.ReuseDetailAty;
import com.lanou3g.autohome.adapter.ReuseAdapter;
import com.lanou3g.autohome.base.BaseFragment;
import com.lanou3g.autohome.tool.GsonRequest;
import com.lanou3g.autohome.tool.InterfaceValues;
import com.lanou3g.autohome.tool.SingleQueue;

/**
 * Created by dllo on 16/8/5.
 */
public class ReuseFragment extends BaseFragment {
    private ListView lvReuse;
    private ReuseAdapter reuseAdapter;
    String url;
    @Override
    protected int getLayout() {
        return R.layout.fragment_reuse;
    }

    @Override
    protected void initView(View view) {
        lvReuse = findView(R.id.lv_reuse);
    }

    @Override
    protected void initData() {
        reuseAdapter = new ReuseAdapter(context);
        Bundle bundle = getArguments();
        if (bundle != null) {
            int position = bundle.getInt("interface");
            url = InterfaceValues.REUSE_URL[position];
        }
        GsonRequest<ReuseBean> gsonRequest = new GsonRequest<ReuseBean>(url, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, new Listener<ReuseBean>() {
            @Override
            public void onResponse(final ReuseBean response) {
                reuseAdapter.setReuseBean(response);
                lvReuse.setAdapter(reuseAdapter);
                lvReuse.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(context, ReuseDetailAty.class);
                        String url = "http://app.api.autohome.com.cn/autov5.0.0/cars/seriesprice-pm2-b" + response.getResult().getList().get(position).getTopicid() + "-t2.json ";
                        intent.putExtra("reuse", url);
                        startActivity(intent);
                    }
                });
            }
        }, ReuseBean.class);
        SingleQueue.getSingleQueue(context).addToRequest(gsonRequest);



    }

    public static ReuseFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("interface",position);
        ReuseFragment multiNewsFragment = new ReuseFragment();
        multiNewsFragment.setArguments(bundle);
        return  multiNewsFragment;
    }
}
