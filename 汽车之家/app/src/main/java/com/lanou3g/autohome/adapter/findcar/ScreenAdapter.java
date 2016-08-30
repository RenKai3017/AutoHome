package com.lanou3g.autohome.adapter.findcar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.autohome.bean.findcar.ScreenBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.base.AutoApplication;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/8/8.
 */
public class ScreenAdapter extends BaseAdapter {
    private ScreenBean screenBean;
    private LayoutInflater inflater;

    public ScreenAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setScreenBean(ScreenBean screenBean) {
        this.screenBean = screenBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return screenBean.getResult().getSeries().size();
    }

    @Override
    public Object getItem(int position) {
        return screenBean.getResult().getSeries().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ScreenHolder screenHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_screen_lv, parent, false);
            screenHolder = new ScreenHolder(convertView);
            convertView.setTag(screenHolder);
        } else {
            screenHolder = (ScreenHolder) convertView.getTag();
        }
        screenHolder.nameTv.setText(screenBean.getResult().getSeries().get(position).getSeriesname());
        screenHolder.priceTv.setText(screenBean.getResult().getSeries().get(position).getPricerange());
        screenHolder.numTv.setText(screenBean.getResult().getSeries().get(position).getCornericon());
        Picasso.with(AutoApplication.getContext()).load(screenBean.getResult().getSeries().get(position).getThumburl()).
                into(screenHolder.screenImg);
        return convertView;
    }


    class ScreenHolder {
        private ImageView screenImg;
        private TextView nameTv, priceTv, numTv;

        public ScreenHolder(View itemView) {
            screenImg = (ImageView) itemView.findViewById(R.id.iv_screen_img);
            nameTv = (TextView) itemView.findViewById(R.id.tv_screen_series);
            priceTv = (TextView) itemView.findViewById(R.id.tv_screen_price);
            numTv = (TextView) itemView.findViewById(R.id.tv_screen_num);
        }
    }
}

