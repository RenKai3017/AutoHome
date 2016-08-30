package com.lanou3g.autohome.guide;

import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.lanou3g.autohome.R;
import com.lanou3g.autohome.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/8/23.
 */
public class GuideActivity extends BaseActivity implements OnPageChangeListener, OnClickListener {
    private ViewPager viewPager;
    private GuideAdapter guideAdapter;
    private List<View>viewList;
    private static final int[] pics = {R.mipmap.lead_one, R.mipmap.lead_two,
                          R.mipmap.lead_three};
    //小点图片
    private ImageView[] points;
    // 记录当前选中位置
    private int currentIndex;
    @Override
    protected int getLayout() {
        return R.layout.activity_guide;

    }

    @Override
    protected void initView() {
        //实例化ArrList（）对象
        viewList = new ArrayList<>();
        viewPager = findView(R.id.viewpager);
        guideAdapter = new GuideAdapter(viewList);

    }

    @Override
    protected void initData() {
        SharedPreferences sp = getSharedPreferences("welcome", MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("first", false);
        editor.apply();
        // 定义一个布局并设置参数
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        //初始化引导图片列表
        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);
            //防止图片不能填满屏幕
            iv.setScaleType(ScaleType.FIT_XY);
            //加载图片资源
            iv.setImageResource(pics[i]);
            viewList.add(iv);
        }
        viewPager.setAdapter(guideAdapter);
        viewPager.setOnPageChangeListener(this);
        initPoint();

    }
    /**
     * 初始化底部小点
     */
    private void initPoint() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);

        points = new ImageView[pics.length];

        // 循环取得小点图片
        for (int i = 0; i < pics.length; i++) {
            // 得到一个LinearLayout下面的每一个子元素
            points[i] = (ImageView) linearLayout.getChildAt(i);
            // 默认都设为灰色
            points[i].setEnabled(true);
            // 给每个小点设置监听
            points[i].setOnClickListener(this);
            // 设置位置tag，方便取出与当前位置对应
            points[i].setTag(i);
        }

        // 设置当面默认的位置
        currentIndex = 0;
        // 设置为白色，即选中状态
        points[currentIndex].setEnabled(false);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 设置底部小点选中状态
        setCurDot(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        setCurView(position);
        setCurDot(position);
    }
    /**
     * 设置当前页面的位置
     */
    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }
        viewPager.setCurrentItem(position);
    }

    /**
     * 设置当前的小点的位置
     */
    private void setCurDot(int positon) {
        if (positon < 0 || positon > pics.length - 1 || currentIndex == positon) {
            return;
        }
        points[positon].setEnabled(false);
        points[currentIndex].setEnabled(true);

        currentIndex = positon;
    }
}
