package com.lanou3g.autohome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou3g.autohome.bean.MultiNewsBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.base.AutoApplication;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by dllo on 16/8/5.
 */
public class MultiNewsAdapter extends BaseAdapter {
    private MultiNewsBean multiNewsBean;
    private LayoutInflater inflater;

    public MultiNewsAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setMultiNewsBean(MultiNewsBean multiNewsBean) {
        this.multiNewsBean = multiNewsBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return multiNewsBean.getResult().getNewslist().size();
    }

    @Override
    public Object getItem(int position) {
        return multiNewsBean.getResult().getNewslist().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MultiNewsHolder multiNewsHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_multi_news_lv, parent, false);
            multiNewsHolder = new MultiNewsHolder(convertView);
            convertView.setTag(multiNewsHolder);
        } else {
            multiNewsHolder = (MultiNewsHolder) convertView.getTag();
        }
        Glide.with(AutoApplication.getContext()).load(multiNewsBean.getResult().getNewslist().get(position).getSmallpic())
                .bitmapTransform(new CropCircleTransformation(AutoApplication.getContext())).into(multiNewsHolder.multiNewsIv);
        multiNewsHolder.titleMultiNewsTv.setText(multiNewsBean.getResult().getNewslist().get(position).getTitle());
        multiNewsHolder.timeMultiNewsTv.setText(multiNewsBean.getResult().getNewslist().get(position).getTime());
        multiNewsHolder.replayMultiNewsTv.setText(multiNewsBean.getResult().getNewslist().get(position).getReplycount() + "评论");
        return convertView;
    }

    class MultiNewsHolder {
        private ImageView multiNewsIv;
        private TextView titleMultiNewsTv, timeMultiNewsTv, replayMultiNewsTv;

        public MultiNewsHolder(View view) {
            multiNewsIv = (ImageView) view.findViewById(R.id.iv_multi_news_image);
            titleMultiNewsTv = (TextView) view.findViewById(R.id.tv_multi_news_title);
            timeMultiNewsTv = (TextView) view.findViewById(R.id.tv_multi_news_time);
            replayMultiNewsTv = (TextView) view.findViewById(R.id.tv_multi_news_reply_count);
        }
    }

}
