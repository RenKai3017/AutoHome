package com.lanou3g.autohome.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.lanou3g.autohome.bean.discover.CarouselBean;
import com.lanou3g.autohome.bean.discover.DiscoverBodyBean;
import com.lanou3g.autohome.bean.discover.DiscoverTwoBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.activity.DiscoverGridAty;
import com.lanou3g.autohome.adapter.discover.AudioAdapter;
import com.lanou3g.autohome.adapter.discover.CarouselAdapter;
import com.lanou3g.autohome.adapter.discover.DiscoverBodyAdapter;
import com.lanou3g.autohome.adapter.discover.DiscoverGridAdapter;
import com.lanou3g.autohome.adapter.discover.DiscoverGridTwoAdapter;
import com.lanou3g.autohome.base.BaseFragment;
import com.lanou3g.autohome.tool.GoTopScrollView;
import com.lanou3g.autohome.tool.InterfaceValues;
import com.lanou3g.autohome.tool.ScreenUtil;
import com.lanou3g.autohome.volleyrequest.VolleySingleQueue;
import com.squareup.picasso.Picasso;


/**
 * Created by dllo on 16/8/2.
 */
public class DiscoverFragment extends BaseFragment{
    private GoTopScrollView mGoTopScrollView;
    private ImageView ivReturnTop;
    private ListView discoverLv;
    private CarouselAdapter carouselAdapter;
    private ViewPager wheelVp;
    private boolean flag = true;
    private boolean lockWheel = true;
    private LinearLayout linearLayoutDot;
    private ImageView[] dots;
    private DiscoverBodyAdapter bodyAdapter;
    private GridView gridDis,_gridDis, audioGrid;
    private DiscoverGridAdapter discoverGridAdapter;
    private DiscoverGridTwoAdapter discoverGridTwoAdapter;
    private TextView gridTitleTv, _gridTitleTv;
    private TextView moreActivityTv;
    private ImageView ivMoveFirst,ivMoveSecond,ivMoveThird;
    private AudioAdapter audioAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void initView(View view) {
        //1.0初始化ScrollView
        mGoTopScrollView= findView(R.id.go_top_scrollView);
        //2.0初始化置顶Icon
        ivReturnTop= findView(R.id.iv_return_top);
        //3.0设置点击置顶的ImageView
        mGoTopScrollView.setImgeViewOnClickGoToFirst(ivReturnTop);
        //4.0获取屏幕高度
        int screenHeight= ScreenUtil.getScreenSize(context)[1];
        //设置ScrollView滑动多少距离就显示，低于多少就显示 如果没有设置就默认为500;(这里我们设置屏幕高度)
        mGoTopScrollView.setScreenHeight(screenHeight);
        //发现——活动专区
        moreActivityTv = findView(R.id.tv_more_activity);
        ivMoveFirst = findView(R.id.iv_more_one);
        ivMoveSecond = findView(R.id.iv_more_two);
        ivMoveThird = findView(R.id.iv_more_three);
        audioGrid = findView(R.id.grid_audio);
        //ListView
        discoverLv = findView(R.id.lv_discover);
        //GridView
        gridDis = findView(R.id.grid_dis);
        _gridDis = findView(R.id._grid_dis);
        gridTitleTv = findView(R.id.tv_grid_title);
        _gridTitleTv = findView(R.id._tv_grid_title);

       // headView = LayoutInflater.from(context).inflate(R.layout.wheel_headers_vp,null);
        wheelVp = findView(R.id.vp_wheel);
        linearLayoutDot = findView(R.id.dot);
        //  discoverLv.addHeaderView(headView);




    }

    @Override
    protected void initData() {
        carouselAdapter = new CarouselAdapter(context);
        bodyAdapter = new DiscoverBodyAdapter(context);
        discoverGridAdapter = new DiscoverGridAdapter(context);
        discoverGridTwoAdapter = new DiscoverGridTwoAdapter(context);
        audioAdapter = new AudioAdapter(context);
        VolleySingleQueue.addRequest(InterfaceValues.DISCOVER_ROTATE_IMG_URL, CarouselBean.class, new Listener<CarouselBean>() {
            @Override
            public void onResponse(CarouselBean response) {
                carouselAdapter.setCarouselBean(response);
                carouselAdapter.setViewPager(wheelVp);
                wheelVp.setAdapter(carouselAdapter);
                wheelFun(response);
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleQueue.addRequest(InterfaceValues.DISCOVER_TWO_URL, DiscoverTwoBean.class, new Listener<DiscoverTwoBean>() {
            @Override
            public void onResponse(final DiscoverTwoBean response) {
                Picasso.with(context).load(response.getResult().getImageads().getImageadsinfo().get(0).getImgurl()).into(ivMoveFirst);
                Picasso.with(context).load(response.getResult().getImageads().getImageadsinfo().get(1).getImgurl()).into(ivMoveSecond);
                Picasso.with(context).load(response.getResult().getImageads().getImageadsinfo().get(2).getImgurl()).into(ivMoveThird);
                ivMoveFirst.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent firstIntent = new Intent(context, DiscoverGridAty.class);
                        String firstUrl = response.getResult().getImageads().getImageadsinfo().get(0).getUrl();
                        firstIntent.putExtra("firstUrl", firstUrl);
                        startActivity(firstIntent);
                    }
                });
                ivMoveSecond.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent secondIntent = new Intent(context, DiscoverGridAty.class);
                        String secondUrl = response.getResult().getImageads().getImageadsinfo().get(1).getUrl();
                        secondIntent.putExtra("secondUrl", secondUrl);
                        startActivity(secondIntent);
                    }
                });
                ivMoveThird.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent thirdIntent = new Intent(context, DiscoverGridAty.class);
                        String thirdUrl = response.getResult().getImageads().getImageadsinfo().get(2).getUrl();
                        thirdIntent.putExtra("thirdUrl", thirdUrl);
                        startActivity(thirdIntent);
                    }
                });
                moreActivityTv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent moreIntent = new Intent(context, DiscoverGridAty.class);
                        String moreUrl = response.getResult().getImageads().getMoreactivitysurl();
                        moreIntent.putExtra("moreUrl", moreUrl);
                        startActivity(moreIntent);
                    }
                });
                audioAdapter.setTwoBean(response);
                audioGrid.setAdapter(audioAdapter);

            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleQueue.addRequest(InterfaceValues.DISCOVER_BODY_URL, DiscoverBodyBean.class, new Listener<DiscoverBodyBean>() {
            @Override
            public void onResponse(final DiscoverBodyBean response) {
                bodyAdapter.setBodyBean(response);
                discoverLv.setAdapter(bodyAdapter);
                discoverLv.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(context, DiscoverGridAty.class);
                        String url = response.getResult().getGoodslist().getList().get(position).getMurl();
                        intent.putExtra("lvUrl",url);
                        startActivity(intent);
                    }
                });
                discoverGridAdapter.setBean(response);
                gridDis.setAdapter(discoverGridAdapter);
                gridTitleTv.setText(response.getResult().getModulelist().get(0).getTitle());
                gridDis.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(context, DiscoverGridAty.class);
                        String url = response.getResult().getModulelist().get(0).getList().get(position).getMurl();
                        intent.putExtra("url",url);
                        startActivity(intent);
                    }
                });
                discoverGridTwoAdapter.setGridTwoBean(response);
                _gridDis.setAdapter(discoverGridTwoAdapter);
                _gridTitleTv.setText(response.getResult().getModulelist().get(1).getTitle());
                _gridDis.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(context, DiscoverGridAty.class);
                        String url = response.getResult().getModulelist().get(1).getList().get(position).getMurl();
                        intent.putExtra("webUrl",url);
                        startActivity(intent);
                    }
                });
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


    }

    private void wheelFun(final CarouselBean carouselBean) {
        final Handler handler = new Handler(new Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                wheelVp.setCurrentItem((wheelVp.getCurrentItem() + 1) % (carouselBean.getResult().getList().size()));
                return false;
            }
        });
        if (lockWheel) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (flag) {

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                        handler.sendEmptyMessage(0);
                    }
                }


            }).start();
            lockWheel = false;
            //当用户点击的时候就不会再触发发轮播图了
            //轮播图就会暂停轮播

        }
        dots = new ImageView[carouselBean.getResult().getList().size()];
        for (int i = 0; i < carouselBean.getResult().getList().size(); i++) {

            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(50, 50));
            dots[i] = imageView;
            if (i == 0) {
                imageView.setImageResource(R.mipmap.umeng_socialize_switchbutton_btn_pressed);
            } else {
                imageView.setImageResource(R.mipmap.umeng_socialize_switchbutton_btn_unpressed);
            }
            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(
                            40, 40);

            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;

            linearLayoutDot.addView(imageView, layoutParams);
        }
        carouselAdapter.setTips(dots);
    }


}
