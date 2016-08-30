package com.lanou3g.autohome.adapter.findcar;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.autohome.bean.findcar.CutBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.base.AutoApplication;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

/**
 * Created by dllo on 16/8/6.
 */
public class CutPriceAdapter extends BaseAdapter {
    private CutBean cutBean;
    private LayoutInflater inflater;

    public CutPriceAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setCutBean(CutBean cutBean) {
        this.cutBean = cutBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return cutBean.getResult().getCarlist() == null ? 0 : cutBean.getResult().getCarlist().size();
    }

    @Override
    public Object getItem(int position) {
        return cutBean.getResult().getCarlist().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CutHolder cutHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_depreciate_lv, parent, false);
            cutHolder = new CutHolder(convertView);
            convertView.setTag(cutHolder);
        } else {
            cutHolder = (CutHolder) convertView.getTag();
        }
        Picasso.with(AutoApplication.getContext()).load(cutBean.getResult().getCarlist().get(position).getSpecpic())
                .into(cutHolder.cutIv);
        cutHolder.seriesNameTv.setText(cutBean.getResult().getCarlist().get(position).getSeriesname());
        cutHolder.specNameTv.setText(cutBean.getResult().getCarlist().get(position).getSpecname());
        cutHolder.dealerPriceTv.setText(cutBean.getResult().getCarlist().get(position).getDealerprice() + "万");
        cutHolder.fctPriceTv.setText(cutBean.getResult().getCarlist().get(position).getFctprice() + "万");
        float dealPrice = Float.parseFloat(cutBean.getResult().getCarlist().get(position).getDealerprice());
        float fctPrice = Float.parseFloat(cutBean.getResult().getCarlist().get(position).getFctprice());
        float cutPrice = fctPrice - dealPrice;
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        String price = decimalFormat.format(cutPrice);
        cutHolder.cutPriceTv.setText("降" + price + "万");
        cutHolder.cityTv.setText(cutBean.getResult().getCarlist().get(position).getDealer().getCity());
        cutHolder.shortNameTv.setText(cutBean.getResult().getCarlist().get(position).getDealer().getShortname());
        cutHolder.distanceTv.setText("距离" + cutBean.getResult().getCarlist().get(position).getDealer().getDistance());
        cutHolder.rangeTv.setText(cutBean.getResult().getCarlist().get(position).getDealer().getOrderrange());

        return convertView;
    }

    class CutHolder {
        private ImageView cutIv;
        private TextView seriesNameTv, specNameTv, dealerPriceTv, fctPriceTv, cutPriceTv;
        private TextView cityTv, shortNameTv,distanceTv,rangeTv;
        public CutHolder(View view) {
            cutIv = (ImageView) view.findViewById(R.id.iv_depreciate);
            seriesNameTv = (TextView) view.findViewById(R.id.tv_series_name);
            specNameTv = (TextView) view.findViewById(R.id.tv_spec_name);

            dealerPriceTv = (TextView) view.findViewById(R.id.tv_dealer_price);
            fctPriceTv = (TextView) view.findViewById(R.id.tv_fct_price);
            fctPriceTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

            cutPriceTv = (TextView) view.findViewById(R.id.tv_cut_price);

            cityTv = (TextView) view.findViewById(R.id.tv_city);
            shortNameTv = (TextView) view.findViewById(R.id.tv_short_name);
            distanceTv = (TextView) view.findViewById(R.id.tv_distance);
            rangeTv = (TextView) view.findViewById(R.id.tv_order_range);
        }
    }

}
