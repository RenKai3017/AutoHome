package com.lanou3g.autohome.fragment.findautofragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.GridView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.lanou3g.autohome.bean.findcar.AllBrandBean;
import com.lanou3g.autohome.bean.findcar.HotBrandBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.adapter.findcar.BrandAdapter;
import com.lanou3g.autohome.adapter.findcar.HotBrandAdapter;
import com.lanou3g.autohome.base.BaseFragment;
import com.lanou3g.autohome.tool.GsonRequest;
import com.lanou3g.autohome.tool.InterfaceValues;
import com.lanou3g.autohome.tool.SingleQueue;

/**
 * Created by dllo on 16/8/3.
 */
public class BrandFragment extends BaseFragment {
    private ExpandableListView expandableListView;
    private BrandAdapter brandAdapter;
    private GridView brandGridView;
    private HotBrandAdapter hotBrandAdapter;
    @Override
    protected int getLayout() {
        return R.layout.fragment_brand;
    }

    @Override
    protected void initView(View view) {
        expandableListView = findView(R.id.expandable_lv);
        expandableListView.setGroupIndicator(null);
        View headView = LayoutInflater.from(context).inflate(R.layout.head_expandable,null);
        brandGridView = (GridView) headView.findViewById(R.id.grid_view);
        expandableListView.addHeaderView(headView);
    }

    @Override
    protected void initData() {
        brandAdapter = new BrandAdapter(context);
        hotBrandAdapter = new HotBrandAdapter(context);
        expandableListView.setOnGroupClickListener(new OnGroupClickListener() {
            //不展开
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });

        GsonRequest<HotBrandBean> hotGsonRequest = new GsonRequest<HotBrandBean>(InterfaceValues.HOT_BRAND_URL, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, new Listener<HotBrandBean>() {
            @Override
            public void onResponse(HotBrandBean response) {
                hotBrandAdapter.setHotBrandBean(response);
                brandGridView.setAdapter(hotBrandAdapter);
            }
        }, HotBrandBean.class);
        SingleQueue.getSingleQueue(context).getRequestQueue().add(hotGsonRequest);

        GsonRequest<AllBrandBean> AllBrandRequest = new GsonRequest<AllBrandBean>(InterfaceValues.ALL_BRAND_URL
                , new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, new Listener<AllBrandBean>() {
            @Override
            public void onResponse(AllBrandBean response) {

                brandAdapter.setAllBrandBean(response);
                expandableListView.setAdapter(brandAdapter);
                int groupCount = brandAdapter.getGroupCount();
                Log.d("BrandFragment", "groupCount:" + groupCount);
                for (int i = 0; i < groupCount; i++) {
                    expandableListView.expandGroup(i);
                }


            }
        }, AllBrandBean.class);
        SingleQueue.getSingleQueue(context).getRequestQueue().add(AllBrandRequest);
    }
}
