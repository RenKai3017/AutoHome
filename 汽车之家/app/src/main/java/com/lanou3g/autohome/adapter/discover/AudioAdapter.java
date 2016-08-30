package com.lanou3g.autohome.adapter.discover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.autohome.bean.discover.DiscoverTwoBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.base.AutoApplication;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/8/9.
 */
public class AudioAdapter extends BaseAdapter {
    private DiscoverTwoBean twoBean;
    private LayoutInflater inflater;

    public AudioAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setTwoBean(DiscoverTwoBean twoBean) {
        this.twoBean = twoBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return twoBean.getResult().getFunctionlist().size();
    }

    @Override
    public Object getItem(int position) {
        return twoBean.getResult().getFunctionlist().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AudioHolder audioHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_discover_audio, parent, false);
            audioHolder = new AudioHolder(convertView);
            convertView.setTag(audioHolder);
        } else {
            audioHolder = (AudioHolder) convertView.getTag();
        }
        Picasso.with(AutoApplication.getContext()).load(twoBean.getResult().getFunctionlist().
                get(position).getIconurl()).into(audioHolder.audioIcon);
        audioHolder.audioTitle.setText(twoBean.getResult().getFunctionlist().get(position).getTitle());


        return convertView;
    }

    class AudioHolder {
        ImageView audioIcon;
        TextView audioTitle;

        public AudioHolder(View view) {
            audioIcon = (ImageView) view.findViewById(R.id.iv_audio_icon);
            audioTitle = (TextView) view.findViewById(R.id.tv_audio_title);


        }
    }
}
