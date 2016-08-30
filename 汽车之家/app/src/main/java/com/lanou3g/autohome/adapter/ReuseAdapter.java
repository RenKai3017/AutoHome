package com.lanou3g.autohome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.autohome.bean.ReuseBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.base.AutoApplication;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/8/5.
 */
public class ReuseAdapter extends BaseAdapter {
    private ReuseBean reuseBean;
    private LayoutInflater inflater;

    public ReuseAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setReuseBean(ReuseBean reuseBean) {
        this.reuseBean = reuseBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return reuseBean.getResult().getList().size();
    }

    @Override
    public Object getItem(int position) {
        return reuseBean.getResult().getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MultiNewsHolder multiNewsHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_reuse_lv, parent, false);
            multiNewsHolder = new MultiNewsHolder(convertView);
            convertView.setTag(multiNewsHolder);
        } else {
            multiNewsHolder = (MultiNewsHolder) convertView.getTag();
        }
        Picasso.with(AutoApplication.getContext()).load(reuseBean.getResult().getList().get(position).getSmallpic())
                .into(multiNewsHolder.reuseIv);
        multiNewsHolder.titleReuseTv.setText(reuseBean.getResult().getList().get(position).getTitle());
        multiNewsHolder.bbsReuseTv.setText(reuseBean.getResult().getList().get(position).getBbsname());
        multiNewsHolder.replayReuseTv.setText(reuseBean.getResult().getList().get(position).getReplycounts() + "回");
        return convertView;
    }

    class MultiNewsHolder {
        private ImageView reuseIv;
        private TextView titleReuseTv, bbsReuseTv, replayReuseTv;

        public MultiNewsHolder(View view) {
            reuseIv = (ImageView) view.findViewById(R.id.iv_reuse_image);
            titleReuseTv = (TextView) view.findViewById(R.id.tv_reuse_title);
            bbsReuseTv = (TextView) view.findViewById(R.id.tv_reuse_bbs_name);
            replayReuseTv = (TextView) view.findViewById(R.id.tv_reuse_reply_count);
        }
    }
}
