package com.lanou3g.autohome.adapter.discover;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.autohome.bean.discover.DiscoverBodyBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.base.AutoApplication;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/8/8.
 */
public class DiscoverBodyAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private DiscoverBodyBean bodyBean;

    public DiscoverBodyAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setBodyBean(DiscoverBodyBean bodyBean) {
        this.bodyBean = bodyBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bodyBean.getResult().getGoodslist().getList().size();
    }

    @Override
    public Object getItem(int position) {
        return bodyBean.getResult().getGoodslist().getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DiscoverBodyHolder bodyHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_discover_lv, parent, false);
            bodyHolder = new DiscoverBodyHolder(convertView);
            convertView.setTag(bodyHolder);
        } else {
            bodyHolder = (DiscoverBodyHolder) convertView.getTag();
        }
        Picasso.with(AutoApplication.getContext()).load(bodyBean.getResult().getGoodslist().getList().get(position).getLogo())
                .into(bodyHolder.disLogoIv);
        bodyHolder.disTitleTv.setText(bodyBean.getResult().getGoodslist().getList().get(position).getTitle());
        bodyHolder.disInfoTv.setText(bodyBean.getResult().getGoodslist().getList().get(position).getAdinfo());
        bodyHolder.disPriceTv.setText(bodyBean.getResult().getGoodslist().getList().get(position).getPrice());
        bodyHolder.disFctTv.setText(bodyBean.getResult().getGoodslist().getList().get(position).getFctprice());

        return convertView;
    }

    class DiscoverBodyHolder {
        private ImageView disLogoIv;
        private TextView disTitleTv, disInfoTv, disPriceTv, disFctTv;
        public DiscoverBodyHolder(View view) {
            disLogoIv = (ImageView) view.findViewById(R.id.iv_discover_logo);
            disTitleTv = (TextView) view.findViewById(R.id.tv_discover_title);
            disInfoTv = (TextView) view.findViewById(R.id.tv_discover_info);
            disPriceTv = (TextView) view.findViewById(R.id.tv_discover_price);
            disFctTv = (TextView) view.findViewById(R.id.tv_discover_fct_price);
            //添加横线
            disFctTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

}
