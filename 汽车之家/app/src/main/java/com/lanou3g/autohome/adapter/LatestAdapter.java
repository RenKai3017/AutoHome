package com.lanou3g.autohome.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.lanou3g.autohome.bean.LatestBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.base.AutoApplication;
import com.lanou3g.autohome.tool.SingleQueue;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/8/3.
 */
public class LatestAdapter extends BaseAdapter {
    private LatestBean latestBean;
    private LayoutInflater inflater;


    public LatestAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setLatestBean(LatestBean latestBean) {
        this.latestBean = latestBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return latestBean.getResult().getNewslist().size();
    }

    private static final int TYPE_THERE = 0;
    private static final int TYPE_ONE = 1;
    @Override
    public Object getItem(int position) {
        return latestBean.getResult().getNewslist().get(position);

    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        LatestHolder latestHolder = null;
        DetailHolder detailHolder = null;
        if (convertView == null) {
            switch (viewType) {
                case TYPE_THERE:
                    convertView = inflater.inflate(R.layout.item_latest_detail, parent, false);
                    detailHolder = new DetailHolder(convertView);
                    convertView.setTag(detailHolder);
                    break;
                case TYPE_ONE:
                    convertView = inflater.inflate(R.layout.item_latest_lv, parent, false);
                    latestHolder = new LatestHolder(convertView);
                    convertView.setTag(latestHolder);
                    break;
            }
        } else {
            switch (viewType) {
                case TYPE_THERE:
                    detailHolder = (DetailHolder) convertView.getTag();
                    break;
                case TYPE_ONE:
                    latestHolder = (LatestHolder) convertView.getTag();
                    break;
            }
        }


        switch (viewType) {
            case TYPE_THERE:
                detailHolder.detailTitle.setText(latestBean.getResult().getNewslist().get(position).getTitle());
                detailHolder.detailTime.setText(latestBean.getResult().getNewslist().get(position).getTime());
                detailHolder.detailReplay.setText(latestBean.getResult().getNewslist().get(position).getReplycount() + "图片");
                String url = latestBean.getResult().getNewslist().get(position).getIndexdetail();
                Log.d("url", "onResponse: " + url);
                            String[] args = url.split("㊣");
                String a = args[2];
                String[] ooo = a.split(",");
                String one = ooo[0];
                String two = ooo[1];
                String thr = ooo[2];

                Picasso.with(AutoApplication.getContext()).load(one).into(detailHolder.detailIconOne);
                Picasso.with(AutoApplication.getContext()).load(two).into(detailHolder.detailIconTwo);
                Picasso.with(AutoApplication.getContext()).load(thr).into(detailHolder.detailIconThere);
                break;
            case TYPE_ONE:
                SingleQueue.getSingleQueue(AutoApplication.getContext()).getImageLoader().get(
                        latestBean.getResult().getNewslist().get(position).getSmallpic(), ImageLoader.getImageListener(
                                latestHolder.latestIv, R.mipmap.ic_launcher, R.mipmap.ic_launcher
                        ));
                latestHolder.titleLatestTv.setText(latestBean.getResult().getNewslist().get(position).getTitle());
                latestHolder.timeLatestTv.setText(latestBean.getResult().getNewslist().get(position).getTime());
                if (latestBean.getResult().getNewslist().get(position).getMediatype() == 1) {

                    latestHolder.replayLatestTv.setText(latestBean.getResult().getNewslist().get(position).getReplycount() + "评论");
                } else if (latestBean.getResult().getNewslist().get(position).getMediatype() == 2) {

                    latestHolder.replayLatestTv.setText(latestBean.getResult().getNewslist().get(position).getReplycount() + "评论");
                } else if (latestBean.getResult().getNewslist().get(position).getMediatype() == 3) {

                    latestHolder.replayLatestTv.setText(latestBean.getResult().getNewslist().get(position).getReplycount() + "播放");
                } else {
                    latestHolder.replayLatestTv.setText(latestBean.getResult().getNewslist().get(position).getReplycount() + "回帖");

                }

                break;
        }


        return convertView;
    }

    class LatestHolder {
         ImageView latestIv;
         TextView titleLatestTv, timeLatestTv, replayLatestTv;

        public LatestHolder(View v) {
            latestIv = (ImageView) v.findViewById(R.id.iv_latest_image);
            titleLatestTv = (TextView) v.findViewById(R.id.tv_latest_title);
            timeLatestTv = (TextView) v.findViewById(R.id.tv_latest_time);
            replayLatestTv = (TextView) v.findViewById(R.id.tv_latest_reply_count);

        }
    }

    class DetailHolder {
         ImageView detailIconOne, detailIconTwo, detailIconThere;
         TextView detailTitle, detailTime, detailReplay;

        public DetailHolder(View v) {
            detailTitle = (TextView) v.findViewById(R.id.detail_title);
            detailIconOne = (ImageView) v.findViewById(R.id.detail_icon_one);
            detailIconTwo = (ImageView) v.findViewById(R.id.detail_icon_two);
            detailIconThere = (ImageView) v.findViewById(R.id.detail_icon_there);
            detailTime = (TextView) v.findViewById(R.id.detail_time);
            detailReplay = (TextView) v.findViewById(R.id.detail_replay);
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return latestBean.getResult().getNewslist().get(position).getMediatype() == 6 ? TYPE_THERE : TYPE_ONE;
    }
}
