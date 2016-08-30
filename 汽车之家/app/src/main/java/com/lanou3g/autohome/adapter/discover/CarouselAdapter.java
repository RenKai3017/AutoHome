package com.lanou3g.autohome.adapter.discover;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lanou3g.autohome.bean.discover.CarouselBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.activity.CarouselAty;
import com.lanou3g.autohome.base.AutoApplication;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/8/8.
 */
public class CarouselAdapter extends PagerAdapter implements OnPageChangeListener {

    private LayoutInflater inflater;
    private ViewPager viewPager;
    private CarouselBean carouselBean;
    private ImageView[] tips;

    public void setTips(ImageView[] tips) {
        this.tips = tips;
        notifyDataSetChanged();
    }

    public void setCarouselBean(CarouselBean carouselBean) {
        this.carouselBean = carouselBean;
        notifyDataSetChanged();
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public CarouselAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 10000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = inflater.inflate(R.layout.item_wheel, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_wheel);
        Picasso.with(AutoApplication.getContext()).load(carouselBean.getResult().getList().
                get(position % (carouselBean.getResult().getList().size())).getImgurl()).into(imageView);

        container.addView(view);
        viewPager.addOnPageChangeListener(this);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AutoApplication.getContext(), CarouselAty.class);
                String url = carouselBean.getResult().getList().get(position).getUrl();
                intent.putExtra("url", url);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                AutoApplication.getContext().startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        for (int i = 0; i < tips.length; i++) {
            if (i == position % carouselBean.getResult().getList().size()) {
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
