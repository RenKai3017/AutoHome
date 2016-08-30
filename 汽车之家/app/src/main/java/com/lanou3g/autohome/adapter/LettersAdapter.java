package com.lanou3g.autohome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lanou3g.autohome.bean.LettersBean;
import com.lanou3g.autohome.R;

/**
 * Created by dllo on 16/8/4.
 */
public class LettersAdapter extends BaseAdapter {
    private LettersBean lettersBean;
    private LayoutInflater inflater;

    public LettersAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setLettersBean(LettersBean lettersBean) {
        this.lettersBean = lettersBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return lettersBean.getResult().getList().size();
    }

    @Override
    public Object getItem(int position) {
        return lettersBean.getResult().getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LettersHolder lettersHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_letters_lv, parent, false);
            lettersHolder = new LettersHolder(convertView);
            convertView.setTag(lettersHolder);
        } else {
            lettersHolder = (LettersHolder) convertView.getTag();
        }
        lettersHolder.titleTv.setText(lettersBean.getResult().getList().get(position).getTitle());
        lettersHolder.reviewTv.setText(lettersBean.getResult().getList().get(position).getReviewcount() + "人浏览");
        lettersHolder.timeTv.setText(lettersBean.getResult().getList().get(position).getCreatetime());
        DraweeController draweeController =
                Fresco.newDraweeControllerBuilder().setUri(lettersBean.getResult().getList().get(position).getBgimage())
                .setAutoPlayAnimations(true)
                .build();
        lettersHolder.simpleDraweeView.setController(draweeController);

        return convertView;
    }

    class LettersHolder {
        TextView titleTv, reviewTv, timeTv;
        SimpleDraweeView simpleDraweeView;

        public LettersHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.tv_title_letters);
            reviewTv = (TextView) view.findViewById(R.id.tv_review_count_letters);
            timeTv = (TextView) view.findViewById(R.id.tv_time_letters);
            simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.iv_img_letters);
        }
    }
}
