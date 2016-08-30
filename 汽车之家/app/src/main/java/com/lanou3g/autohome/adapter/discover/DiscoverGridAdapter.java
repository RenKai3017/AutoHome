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
public class DiscoverGridAdapter extends BaseAdapter {
    private DiscoverBodyBean bean;
    private LayoutInflater inflater;

    public DiscoverGridAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setBean(DiscoverBodyBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bean.getResult().getModulelist().get(0).getList().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getResult().getModulelist().get(0).getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DiscoverGridHolder gridHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_discover_grid, parent, false);
            gridHolder = new DiscoverGridHolder(convertView);
            convertView.setTag(gridHolder);
        } else {
            gridHolder = (DiscoverGridHolder) convertView.getTag();
        }
        Picasso.with(AutoApplication.getContext()).load(bean.getResult().getModulelist().get(0).getList().
                get(position).getLogo()).into(gridHolder.gridLogo);
        gridHolder.gridTitle.setText(bean.getResult().getModulelist().get(0).getList().get(position).getTitle());
        gridHolder.gridShortTitle.setText(bean.getResult().getModulelist().get(0).getList().get(position).getShorttitle());
        gridHolder.gridPrice.setText(bean.getResult().getModulelist().get(0).getList().get(position).getPrice());
        gridHolder.gridFctPrice.setText(bean.getResult().getModulelist().get(0).getList().get(position).getFctprice());

        return convertView;
    }

    class DiscoverGridHolder {
        ImageView gridLogo;
        TextView gridTitle, gridShortTitle, gridPrice, gridFctPrice;

        public DiscoverGridHolder(View view) {
            gridLogo = (ImageView) view.findViewById(R.id.iv_grid_logo);
            gridTitle = (TextView) view.findViewById(R.id.tv_grid_title);
            gridShortTitle = (TextView) view.findViewById(R.id.tv_grid_short_title);
            gridPrice = (TextView) view.findViewById(R.id.tv_grid_price);
            gridFctPrice = (TextView) view.findViewById(R.id.tv_grid_fct);
            gridFctPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        }
    }

}
