package com.lanou3g.autohome.homereuse;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.activity.WebViewActivity;
import com.lanou3g.autohome.activity.detail.BulletinDetailActivity;
import com.lanou3g.autohome.adapter.LatestAdapter;
import com.lanou3g.autohome.adapter.LettersAdapter;
import com.lanou3g.autohome.adapter.MultiNewsAdapter;
import com.lanou3g.autohome.adapter.WheelAdapter;
import com.lanou3g.autohome.base.BaseFragment;
import com.lanou3g.autohome.bean.LatestBean;
import com.lanou3g.autohome.bean.LettersBean;
import com.lanou3g.autohome.bean.MultiNewsBean;
import com.lanou3g.autohome.tool.GsonRequest;
import com.lanou3g.autohome.tool.InterfaceValues;
import com.lanou3g.autohome.tool.SingleQueue;
import com.lanou3g.autohome.volleyrequest.VolleySingleQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/8/5.
 */
public class HomeReuseFragment extends BaseFragment implements OnItemClickListener {
    private ListView listView;
    private String url;
    private ViewPager wheelVp;
    private LinearLayout linearLayoutDot;
    private LatestAdapter latestAdapter;
    private WheelAdapter wheelAdapter;
    private ImageView[] dots;
    private boolean flag = true;
    private boolean lockWheel = true;
    private View headerView;
    private RelativeLayout relativeLayout;
    private LettersAdapter lettersAdapter;
    List<LatestBean> datas = new ArrayList<>();
    List<MultiNewsBean>data = new ArrayList<>();
    private MultiNewsAdapter multiNewsAdapter;
    LatestBean latestBean;
    LettersBean lettersBean;
    private PullToRefreshListView pullToRefreshListView;//刷新
    private ILoadingLayout loadingLayout;

    @Override
    protected int getLayout() {
        Fresco.initialize(context);//一定要在setContentView之前写
        return R.layout.home_reuse;
    }

    @Override
    protected void initView(View view) {
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.lv_home_reuse);
        //设置下拉
        pullToRefreshListView.setMode(Mode.BOTH);
        //创建listView
        listView = pullToRefreshListView.getRefreshableView();
        headerView = LayoutInflater.from(context).inflate(R.layout.wheel_headers_vp, null);
        wheelVp = (ViewPager) headerView.findViewById(R.id.vp_wheel);
        linearLayoutDot = (LinearLayout) headerView.findViewById(R.id.dot);
        relativeLayout = findView(R.id.rl_home);
    }

    @Override
    protected void initData() {
        loadingLayout = pullToRefreshListView.getLoadingLayoutProxy(true, false);
        loadingLayout.setRefreshingLabel("正在刷新");
        loadingLayout.setReleaseLabel("释放开始刷新");

        ILoadingLayout startLabelsNext = pullToRefreshListView.getLoadingLayoutProxy(false, true);
        startLabelsNext.setRefreshingLabel("正在加载");
        startLabelsNext.setPullLabel("上拉加载更多");
        //初始化动画

        latestAdapter = new LatestAdapter(context);
        wheelAdapter = new WheelAdapter(context);
        pullToRefreshListView.setOnItemClickListener(this);
        Bundle bundle = getArguments();
        if (bundle != null) {
            final int position = bundle.getInt("home");
            url = InterfaceValues.HOME_URL[position];
            switch (position) {
                case 0:
                    //获取最新页面数据
                    GsonRequest<LatestBean> gsonRequest = new GsonRequest<LatestBean>(url, new ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }, new Listener<LatestBean>() {
                        @Override
                        public void onResponse(final LatestBean response) {
                            latestBean = response;
                            datas.add(response);
                            latestAdapter.setLatestBean(response);
                            wheelAdapter.setLatestBean(response);
                            wheelAdapter.setViewPager(wheelVp);
                            listView.addHeaderView(headerView);
                            pullToRefreshListView.setAdapter(latestAdapter);
                            wheelVp.setAdapter(wheelAdapter);
                            wheelFun(response);


                        }

                    }, LatestBean.class);
                    SingleQueue.getSingleQueue(context).getRequestQueue().add(gsonRequest);
                    //设置刷新

                    pullToRefreshListView.setOnRefreshListener(new OnRefreshListener2<ListView>() {
                        //下拉刷新
                        @Override
                        public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                            VolleySingleQueue.addRequest(url, LatestBean.class, new Listener<LatestBean>() {
                                @Override
                                public void onResponse(LatestBean response) {
                                    latestBean = response;
                                    latestAdapter.setLatestBean(latestBean);
                                    //刷新停止
                                    pullToRefreshListView.onRefreshComplete();
                                    //设置下拉的中的时间
                                    String str = DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.
                                            FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                                    loadingLayout.setLastUpdatedLabel("最后更新时间:" + str);
                                    Toast.makeText(context, "刷新数据成功", Toast.LENGTH_SHORT).show();
                                    linearLayoutDot.removeAllViews();
                                    wheelFun(latestBean);
                                }
                            }, new ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });
                        }
                        //上拉加载
                        @Override
                        public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                            int latestId = latestBean.getResult().getNewslist().get(latestBean.getResult().getNewslist().size() - 1).getId();
                            String latestTime = latestBean.getResult().getNewslist().get(latestBean.getResult().getNewslist().size() - 1).getLasttime();
                            String updateUrl = "http://app.api.autohome.com.cn/autov4.2.5/news/newslist-a2-pm1-v4.2.5-c0-nt0-p"
                                    + latestId + "-s30-l" + latestTime + ".html";
                            VolleySingleQueue.addRequest(updateUrl, LatestBean.class, new Listener<LatestBean>() {
                                @Override
                                public void onResponse(LatestBean response) {
                                    latestBean.getResult().getNewslist().addAll(response.getResult().getNewslist());
                                    latestAdapter.setLatestBean(latestBean);
                                    //刷新停止
                                    pullToRefreshListView.onRefreshComplete();
                                }
                            }, new ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });

                        }
                    });
                    break;
                case 1:
                    lettersAdapter = new LettersAdapter(context);
                    relativeLayout.setVisibility(View.VISIBLE);
                    final GsonRequest<LettersBean> lettersRequest = new GsonRequest<LettersBean>(url, new ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }, new Listener<LettersBean>() {
                        @Override
                        public void onResponse(LettersBean response) {
                            lettersBean = response;
                            lettersAdapter.setLettersBean(response);
                            pullToRefreshListView.setAdapter(lettersAdapter);
                        }
                    }, LettersBean.class);
                    SingleQueue.getSingleQueue(context).getRequestQueue().add(lettersRequest);
                    pullToRefreshListView.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent letterIntent = new Intent();
                                int letterId = lettersBean.getResult().getList().get(position - 1).getId();
                                letterIntent.setClass(context, BulletinDetailActivity.class);
                                letterIntent.putExtra("id", letterId);
                            startActivity(letterIntent);
                        }
                    });



                    break;
                default:
                    multiNewsAdapter = new MultiNewsAdapter(context);
                    GsonRequest<MultiNewsBean> multiRequest = new GsonRequest<MultiNewsBean>(url, new ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }, new Listener<MultiNewsBean>() {
                        @Override
                        public void onResponse(MultiNewsBean response) {
                            data.add(response);
                            multiNewsAdapter.setMultiNewsBean(response);
                            pullToRefreshListView.setAdapter(multiNewsAdapter);
                        }
                    }, MultiNewsBean.class);
                    SingleQueue.getSingleQueue(context).getRequestQueue().add(multiRequest);

                    break;
            }
        }

    }

    private void wheelFun(final LatestBean latestBean) {
        final Handler handler = new Handler(new Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                wheelVp.setCurrentItem((wheelVp.getCurrentItem() + 1) % (latestBean.getResult().getFocusimg().size()));
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
        dots = new ImageView[latestBean.getResult().getFocusimg().size()];
        for (int i = 0; i < latestBean.getResult().getFocusimg().size(); i++) {

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
        wheelAdapter.setTips(dots);

    }

    public static HomeReuseFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("home", position);
        HomeReuseFragment homeReuseFragment = new HomeReuseFragment();
        homeReuseFragment.setArguments(bundle);
        return homeReuseFragment;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(context, WebViewActivity.class);
        String url = "http://cont.app.autohome.com.cn/autov4.2.5/content/News/newscontent-a2-pm1-v4.2.5-n" + latestBean.getResult().getNewslist().get(position - 2).getId() + "-lz0-sp0-nt0-sa1-p0-c1-fs0-cw320.html";
        intent.putExtra("url", url);
        int cId = latestBean.getResult().getNewslist().get(position - 2).getId();
        String title = latestBean.getResult().getNewslist().get(position - 2).getTitle();
        String collectionUrl = url;
        String cSmallPic = latestBean.getResult().getNewslist().get(position - 2).getSmallpic();
        Collection collection = new Collection((long) cId,title,collectionUrl,cSmallPic);
        intent.putExtra("Collection", collection);
        intent.putExtra("Title", title);
        intent.putExtra("SmallPic", cSmallPic);
        startActivity(intent);
    }
}
