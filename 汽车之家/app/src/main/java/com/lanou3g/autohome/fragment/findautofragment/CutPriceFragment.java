package com.lanou3g.autohome.fragment.findautofragment;

import android.view.View;
import android.widget.ListView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.lanou3g.autohome.bean.findcar.CutBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.adapter.findcar.CutPriceAdapter;
import com.lanou3g.autohome.base.BaseFragment;
import com.lanou3g.autohome.tool.GsonRequest;
import com.lanou3g.autohome.tool.InterfaceValues;
import com.lanou3g.autohome.tool.SingleQueue;

/**
 * Created by dllo on 16/8/3.
 */
public class CutPriceFragment extends BaseFragment {
    private ListView cutPriceLv;
    private CutPriceAdapter cutPriceAdapter;
    @Override
    protected int getLayout() {
        return R.layout.fragment_cut_price;
    }

    @Override
    protected void initView(View view) {
        cutPriceLv = findView(R.id.cut_price_lv);
    }

    @Override
    protected void initData() {
        cutPriceAdapter = new CutPriceAdapter(context);

        GsonRequest<CutBean> cutRequest = new GsonRequest<CutBean>(InterfaceValues.DEPRECIATE_URL, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, new Listener<CutBean>() {
            @Override
            public void onResponse(CutBean response) {
                cutPriceAdapter.setCutBean(response);
                cutPriceLv.setAdapter(cutPriceAdapter);
            }
        }, CutBean.class);
        SingleQueue.getSingleQueue(context).getRequestQueue().add(cutRequest);
    }
}
