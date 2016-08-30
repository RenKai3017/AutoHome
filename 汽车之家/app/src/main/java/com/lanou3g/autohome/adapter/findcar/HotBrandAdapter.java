package com.lanou3g.autohome.adapter.findcar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.autohome.bean.findcar.HotBrandBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.base.AutoApplication;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/8/7.
 */
public class HotBrandAdapter extends BaseAdapter{
    private HotBrandBean hotBrandBean;
    private LayoutInflater inflater;

    public HotBrandAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setHotBrandBean(HotBrandBean hotBrandBean) {
        this.hotBrandBean = hotBrandBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return hotBrandBean.getResult().getList().size();
    }

    @Override
    public Object getItem(int position) {
        return hotBrandBean.getResult().getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HotBrandHolder hotBrandHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_hot_brand_grid, parent, false);
            hotBrandHolder = new HotBrandHolder(convertView);
            convertView.setTag(hotBrandHolder);
        } else {
            hotBrandHolder = (HotBrandHolder) convertView.getTag();
        }
        Picasso.with(AutoApplication.getContext()).load(hotBrandBean.getResult().getList().get(position).getImg()).
                        into(hotBrandHolder.hotBrandIv);
        hotBrandHolder.hotNameTv.setText(hotBrandBean.getResult().getList().get(position).getName());
        return convertView;
    }

    class HotBrandHolder {
        private ImageView hotBrandIv;
        private TextView hotNameTv;

        public HotBrandHolder(View itemView) {
            hotBrandIv = (ImageView) itemView.findViewById(R.id.iv_hot_brand);
            hotNameTv = (TextView) itemView.findViewById(R.id.tv_hot_name);
        }

    }
}
