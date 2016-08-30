package com.lanou3g.autohome.tool;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.lanou3g.autohome.R;
/**
 * Created by dllo on 16/8/10.
 */
public class RefreshLoadLayout extends SwipeRefreshLayout implements OnScrollListener {
    private int muchTop;//滑动到最下面的上拉操作
    private ListView mListView;
    private View mListViewFooter;
    private int mYDown;//按下时y坐标
    private int mLastY;//抬起时的y坐标
    private boolean isLoading;
    private OnLoadListener mOnLoadListener;//上拉加载监听


    public RefreshLoadLayout(Context context) {
        this(context,null);
    }

    public RefreshLoadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //表示触发事件的最小距离
        muchTop = ViewConfiguration.get(context).getScaledDoubleTapSlop();
        mListViewFooter = LayoutInflater.from(context).inflate(R.layout.listview_footer, null, false);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //初始化ListView对象
        if (mListView == null) {
            int child = getChildCount();
            if (child > 0) {
                View childView = getChildAt(0);
                if (childView instanceof ListView) {
                    mListView = (ListView) childView;
                    mListView.setOnScrollListener(this);
                }
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mYDown = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                if (canLoad()) {
                    loadData();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                mLastY = (int) ev.getRawY();
                break;
            default:
                break;

        }
        return super.dispatchTouchEvent(ev);
    }
    //判断是否可以加载更多
    private boolean canLoad() {
        return isBottom() && isLoading && isPullUp();
    }
    //判断是否到达底部
    private boolean isBottom() {
        if (mListView != null && mListView.getAdapter() != null) {
            return mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount()-1);
        }
        return false;
    }

    //判断是否是上拉操作
    private boolean isPullUp() {
        return (mYDown - mLastY) >= muchTop;
    }

    //加载操作
    private void loadData() {
        if (mOnLoadListener != null) {
            setLoading(true);
            mOnLoadListener.onLoad();
        }
    }

    public void setLoading(boolean loading) {
        this.isLoading = loading;
//        if (isLoading) {
//           // mListView.addFooterView(mListViewFooter);
//        } else {
//           // mListView.removeFooterView(mListViewFooter);
//            mYDown = 0;
//            mLastY = 0;
//        }

    }

    public void setOnLoadListener(OnLoadListener mOnLoadListener) {
        this.mOnLoadListener = mOnLoadListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (canLoad()) {
            loadData();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    public  interface OnLoadListener {
        void onLoad();
    }


}
