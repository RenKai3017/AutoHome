package com.lanou3g.autohome.fragment.forumfragment;

import android.text.Layout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lanou3g.autohome.R;
import com.lanou3g.autohome.base.BaseFragment;
import com.lanou3g.autohome.eventbus.DataBean;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;

/**
 * Created by dllo on 16/8/2.
 */
public class CommonForumFragment extends BaseFragment implements OnClickListener {
    private TextView areaTv;
    @Override
    protected int getLayout() {
        return R.layout.fragment_common_forum;
    }

    @Override
    protected void initView(View view) {
        findView(R.id.series_linea).setOnClickListener(this);
        findView(R.id.area_linea).setOnClickListener(this);
        findView(R.id.theme_linea).setOnClickListener(this);
        areaTv = findView(R.id.tv_area);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.series_linea:
                EventBus.getDefault().post(new DataBean(1));
                break;
            case R.id.area_linea:
                EventBus.getDefault().post(new DataBean(2));
//                EventBus.getDefault().post(areaTv);
                break;
            case R.id.theme_linea:
                EventBus.getDefault().post(new DataBean(3));
                break;
        }
    }
}
