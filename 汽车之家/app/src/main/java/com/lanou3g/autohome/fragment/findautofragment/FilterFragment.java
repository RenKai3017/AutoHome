package com.lanou3g.autohome.fragment.findautofragment;

import android.view.View;
import android.widget.ListView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.adapter.findcar.ScreenAdapter;
import com.lanou3g.autohome.base.BaseFragment;
import com.lanou3g.autohome.bean.findcar.ScreenBean;
import com.lanou3g.autohome.tool.InterfaceValues;
import com.lanou3g.autohome.volleyrequest.VolleySingleQueue;

/**
 * Created by dllo on 16/8/3.
 */
public class FilterFragment extends BaseFragment {
    private ListView screenLv;
    private ScreenAdapter screenAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_filter;
    }

    @Override
    protected void initView(View view) {
        screenLv = findView(R.id.lv_screening);
    }

    @Override
    protected void initData() {
        screenAdapter = new ScreenAdapter(context);
        VolleySingleQueue.addRequest(InterfaceValues.SCREENING_URL, ScreenBean.class, new Listener<ScreenBean>() {
            @Override
            public void onResponse(ScreenBean response) {
                screenAdapter.setScreenBean(response);
                screenLv.setAdapter(screenAdapter);

            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }


}
