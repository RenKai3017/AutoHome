package com.lanou3g.autohome.adapter.forum;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanou3g.autohome.bean.forum.TopBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.adapter.forum.TopAdapter.TopHolder;

/**
 * Created by dllo on 16/8/6.
 */
public class TopAdapter extends Adapter<TopHolder> {
    private LayoutInflater inflater;
    private TopBean topBean;

    public TopAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setTopBean(TopBean topBean) {
        this.topBean = topBean;
        notifyDataSetChanged();
    }

    @Override
    public TopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_hot_top_rv, parent, false);
        TopHolder topHolder = new TopHolder(itemView);
        return topHolder;
    }

    @Override
    public void onBindViewHolder(TopHolder holder, int position) {
        String date = topBean.getResult().getList().get(position).getPostdate().substring(5, 16);
        holder.topTitle.setText(topBean.getResult().getList().get(position).getTitle());
        holder.topBBS.setText(topBean.getResult().getList().get(position).getBbsname());
        holder.topDate.setText(date);
        holder.topReplay.setText(topBean.getResult().getList().get(position).getReplycounts() + "回帖");
    }

    @Override
    public int getItemCount() {
        return topBean.getResult().getList().size();
    }

    class TopHolder extends ViewHolder {
        private TextView topTitle, topBBS, topDate, topReplay;

        public TopHolder(View itemView) {
            super(itemView);
            topTitle = (TextView) itemView.findViewById(R.id.tv_top_title);
            topBBS = (TextView) itemView.findViewById(R.id.tv_top_bbs);
            topDate = (TextView) itemView.findViewById(R.id.tv_top_date);
            topReplay = (TextView) itemView.findViewById(R.id.tv_top_replay);
        }
    }
}
