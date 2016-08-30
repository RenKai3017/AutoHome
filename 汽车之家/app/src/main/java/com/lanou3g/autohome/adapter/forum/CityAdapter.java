package com.lanou3g.autohome.adapter.forum;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou3g.autohome.bean.forum.CityBean;
import com.lanou3g.autohome.R;

/**
 * Created by dllo on 16/8/12.
 */
public class CityAdapter extends Adapter<ViewHolder> {
    public static final int FIRST_STICKY_VIEW = 1;
    public static final int HAS_STICKY_VIEW = 2;
    public static final int NONE_STICKY_VIEW = 3;

    private LayoutInflater inflater;
    private CityBean cityBean;
    private onItemClickListener listener;
    public CityAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setCityBean(CityBean cityBean) {
        this.cityBean = cityBean;
        notifyDataSetChanged();
    }

    public interface onItemClickListener {
        void itemClick(int pos);
    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_rv, parent, false);
        return new CityHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof CityHolder) {
            CityHolder cityHolder = (CityHolder) holder;
            cityHolder.tvName.setText(cityBean.getResult()
            .getList().get(position).getBbsname());

            cityHolder.rlContent.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.itemClick(position);
                }
            });
            if (position == 0) {
                cityHolder.tvHeader.setVisibility(View.VISIBLE);
                cityHolder.tvHeader.setText(cityBean.getResult().getList()
                        .get(position).getFirstletter());
                cityHolder.itemView.setTag(FIRST_STICKY_VIEW);
            } else  {
                if (!TextUtils.equals(cityBean.getResult().getList()
                        .get(position).getFirstletter(), cityBean.getResult()
                        .getList().get(position - 1).getFirstletter())) {
                    cityHolder.tvHeader.setVisibility(View.VISIBLE);
                    cityHolder.tvHeader.setText(cityBean.getResult().getList()
                            .get(position).getFirstletter());
                    cityHolder.itemView.setTag(HAS_STICKY_VIEW);
                } else {
                    cityHolder.tvHeader.setVisibility(View.GONE);
                    cityHolder.itemView.setTag(NONE_STICKY_VIEW);

                }
            }
            cityHolder.itemView.setContentDescription(cityBean.getResult().getList()
                .get(position).getFirstletter());
        }
    }

    @Override
    public int getItemCount() {
        return cityBean.getResult().getList().size();
    }

    class CityHolder extends ViewHolder {
        TextView tvHeader, tvName;
        RelativeLayout rlContent;
        public CityHolder(View itemView) {
            super(itemView);
            tvHeader = (TextView) itemView.findViewById(R.id.header_title);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            rlContent = (RelativeLayout) itemView.findViewById(R.id.rl_content);

        }
    }




}
