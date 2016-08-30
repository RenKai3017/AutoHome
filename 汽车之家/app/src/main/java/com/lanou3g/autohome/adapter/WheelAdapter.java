package com.lanou3g.autohome.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lanou3g.autohome.bean.LatestBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.base.AutoApplication;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/8/4.
 */
public class WheelAdapter extends PagerAdapter implements OnPageChangeListener{
    private LayoutInflater inflater;
    private ViewPager viewPager;
    private LatestBean latestBean;
    private ImageView[] tips;

    public void setTips(ImageView[] tips) {
        this.tips = tips;
        notifyDataSetChanged();
    }

    public void setLatestBean(LatestBean latestBean) {
        this.latestBean = latestBean;
        notifyDataSetChanged();
    }



    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        Log.d("WheelAdapter", "viewPager:" + viewPager);
    }

    public WheelAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.item_wheel, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_wheel);
        Picasso.with(AutoApplication.getContext()).load(latestBean.getResult().getFocusimg().
                get(position % (latestBean.getResult().getFocusimg().size())).getImgurl()).into(imageView);
        container.addView(view);
        viewPager.addOnPageChangeListener(this);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        for (int i = 0; i < tips.length; i++) {
            if (i == position % latestBean.getResult().getFocusimg().size()) {
                tips[i].setImageResource(R.mipmap.umeng_socialize_switchbutton_btn_pressed);
            } else {
                tips[i].setImageResource(R.mipmap.umeng_socialize_switchbutton_btn_unpressed);
            }
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
