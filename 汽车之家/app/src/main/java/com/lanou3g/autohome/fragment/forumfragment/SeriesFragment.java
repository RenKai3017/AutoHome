package com.lanou3g.autohome.fragment.forumfragment;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.autohome.bean.findcar.AllBrandBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.adapter.findcar.BrandAdapter;
import com.lanou3g.autohome.base.BaseFragment;
import com.lanou3g.autohome.tool.AllInterface;
import com.lanou3g.autohome.volleyrequest.VolleySingleQueue;

/**
 * Created by dllo on 16/8/11.
 */
public class SeriesFragment extends BaseFragment {
    private ExpandableListView expandableListView;
    private TextView close;
    private BrandAdapter brandAdapter;
    @Override
    protected int getLayout() {
        return R.layout.fragment_series;
    }

    @Override
    protected void initView(View view) {
        expandableListView = (ExpandableListView) getView().findViewById(R.id.theme_lest_view);
    }
    @Override
    protected void initData() {
        brandAdapter = new BrandAdapter(context);
        VolleySingleQueue.addRequest(AllInterface.ALL_BRAND_URL, AllBrandBean.class, new Response.Listener<AllBrandBean>() {
            @Override
            public void onResponse(AllBrandBean response) {
                brandAdapter.setAllBrandBean(response);
                expandableListView.setAdapter(brandAdapter);
                for (int i = 0; i < brandAdapter.getGroupCount(); i++) {
                    expandableListView.expandGroup(i);
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.getMessage();
            }
        });

    }
}
