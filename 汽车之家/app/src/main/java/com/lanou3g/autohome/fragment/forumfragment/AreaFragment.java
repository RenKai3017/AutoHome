package com.lanou3g.autohome.fragment.forumfragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.lanou3g.autohome.bean.forum.CityBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.adapter.forum.CityAdapter;
import com.lanou3g.autohome.adapter.forum.CityAdapter.onItemClickListener;
import com.lanou3g.autohome.base.BaseFragment;
import com.lanou3g.autohome.tool.CircleTextView;
import com.lanou3g.autohome.tool.InterfaceValues;
import com.lanou3g.autohome.tool.MySlideView;
import com.lanou3g.autohome.tool.MySlideView.onTouchListener;
import com.lanou3g.autohome.volleyrequest.VolleySingleQueue;

/**
 * Created by dllo on 16/8/11.
 */
public class AreaFragment extends BaseFragment implements onItemClickListener, onTouchListener {
    private RecyclerView recyclerView;
    private TextView headerTv;
    private CityAdapter cityAdapter;
    private CircleTextView circleTxt;
    private CityBean cityBean;
    private MySlideView mySlideView;
    @Override
    protected int getLayout() {
        return R.layout.fragment_area;
    }

    @Override
    protected void initView(View view) {
        mySlideView = findView(R.id.slide_bar);
        circleTxt = findView(R.id.my_circle_view);
        mySlideView.setListener(this);
        recyclerView = findView(R.id.rv);
        headerTv = findView(R.id.header_title);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);

    }

    @Override
    protected void initData() {
        cityAdapter = new CityAdapter(context);
        cityAdapter.setListener(this);
        VolleySingleQueue.addRequest(InterfaceValues.REGION_BBS_URL, CityBean.class, new Listener<CityBean>() {
            @Override
            public void onResponse(CityBean response) {
                cityBean = response;
                cityAdapter.setCityBean(response);
                recyclerView.setAdapter(cityAdapter);
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        recyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {/**滑动停止**/
//                    Log.d("test", "recyclerView总共的Item个数:" +
//                            String.valueOf(recyclerView.getLayoutManager().getItemCount() - 1));
//                    Log.d("test", "recyclerView可见的Item个数:" +
//                            String.valueOf(recyclerView.getChildCount()));
//                    /**
//                     * 监听是否滑动到底部
//                     */
//                    //获取可见的最后一个view
//                    View lastChildView = recyclerView.getChildAt(
//                            recyclerView.getChildCount() - 1);
//
//
//                    //获取可见的最后一个view的位置
//                    int lastChildViewPosition = recyclerView.getChildAdapterPosition(lastChildView);
//
//                    //判断lastPosition是不是最后一个position
//                    if (lastChildViewPosition == recyclerView.getLayoutManager().getItemCount() - 1) {
//                        Toast.makeText(MainActivity.this, "滑动到底部了", Toast.LENGTH_SHORT).show();
//                    }
//
//                    /**监听是否滑动到顶部**/
//                    //获取可见的第一个view
//                    View firstVisibleView = recyclerView.getChildAt(0);
//
//                    //获取可见的第一个view的位置
//                    int firstVisiblePosition = recyclerView.getChildAdapterPosition(firstVisibleView);
//
//                    if (firstVisiblePosition == 0) {
//                        Toast.makeText(MainActivity.this, "滑动到顶部了", Toast.LENGTH_SHORT).show();
//                    }
//                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
//                    /**手指还在recycleview上**/
//                } else if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
//                    /**手指离开屏幕，单recycleview仍在滚动**/
//                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View stickyInfoView = recyclerView.findChildViewUnder(headerTv.getMeasuredWidth() / 2, 5);

                if (stickyInfoView != null && stickyInfoView.getContentDescription() != null) {
                    headerTv.setText(String.valueOf(stickyInfoView.getContentDescription()));
                }

                View transInfoView = recyclerView.findChildViewUnder(
                        headerTv.getMeasuredWidth() / 2, headerTv.getMeasuredHeight() + 1);

                if (transInfoView != null && transInfoView.getTag() != null) {
                    int transViewStatus = (int) transInfoView.getTag();
                    int dealtY = transInfoView.getTop() - headerTv.getMeasuredHeight();
                    if (transViewStatus == CityAdapter.HAS_STICKY_VIEW) {
                        if (transInfoView.getTop() > 0) {
                            headerTv.setTranslationY(dealtY);
                        } else {
                            headerTv.setTranslationY(0);
                        }
                    } else if (transViewStatus == CityAdapter.NONE_STICKY_VIEW) {
                        headerTv.setTranslationY(0);
                    }
                }

            }
        });

    }

    @Override
    public void itemClick(int pos) {
        Toast.makeText(context, "你选择了:" + cityBean.getResult().getList()
                .get(pos).getBbsname(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTextView(String textView, boolean dismiss) {
        if (dismiss) {
            circleTxt.setVisibility(View.GONE);
        } else {
            circleTxt.setVisibility(View.VISIBLE);
            circleTxt.setText(textView);
        }
        int selectPosition = 0;
        for (int i = 0; i < cityBean.getResult().getList().size(); i++) {
            if (cityBean.getResult().getList().get(i).getFirstletter().equals(textView)) {
                selectPosition = i;
                break;
            }
        }
        recyclerView.scrollToPosition(selectPosition);
    }
}
