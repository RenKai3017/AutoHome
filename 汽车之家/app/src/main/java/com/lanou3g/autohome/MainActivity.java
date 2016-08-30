package com.lanou3g.autohome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou3g.autohome.base.BaseActivity;
import com.lanou3g.autohome.eventbus.DataBean;
import com.lanou3g.autohome.fragment.DiscoverFragment;
import com.lanou3g.autohome.fragment.FindAutoFragment;
import com.lanou3g.autohome.fragment.ForumFragment;
import com.lanou3g.autohome.fragment.RecommendFragment;
import com.lanou3g.autohome.fragment.forumfragment.AreaFragment;
import com.lanou3g.autohome.fragment.forumfragment.SeriesFragment;
import com.lanou3g.autohome.fragment.forumfragment.ThemeFragment;
import com.lanou3g.autohome.fragment.my.MineFragment;
import com.lanou3g.autohome.tool.ExampleUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends BaseActivity implements OnClickListener{
    private FragmentTransaction transaction;
    private DrawerLayout drawerAll;
    private TextView titleTv;
    public static boolean isForeground = false;

    private List<Fragment>fragmentList;
    private FragmentManager manager;

    private static boolean isExit;
    //Timer是一个计时器
    Timer timer = new Timer();
    TimerTask timerTask;

    @Override
    protected int getLayout() {
        EventBus.getDefault().register(this);

        return R.layout.activity_main;

    }

    @Override
    protected void initView() {
        drawerAll = findView(R.id.drawer_all);
        findView(R.id.recommend_rdo).setOnClickListener(this);
        findView(R.id.forum_rdo).setOnClickListener(this);
        findView(R.id.look_auto_rdo).setOnClickListener(this);
        findView(R.id.discover_rdo).setOnClickListener(this);
        findView(R.id.mine_rdo).setOnClickListener(this);
        findView(R.id.close_drawer).setOnClickListener(this);
        titleTv = findView(R.id.title_tv);
        registerMessageReceiver();  // used for receive msg 推送

    }

    @Override
    protected void initData() {
        drawerAll.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerAll.addDrawerListener(new DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                drawerAll.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                //关闭手势滑动
                drawerAll.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        //记录每一个位置
        manager = getSupportFragmentManager();
        fragmentList = new ArrayList<>();
        fragmentList.add(new RecommendFragment());
        fragmentList.add(new ForumFragment());
        fragmentList.add(new FindAutoFragment());
        fragmentList.add(new DiscoverFragment());
        fragmentList.add(new MineFragment());
        manager.beginTransaction()
                .add(R.id.replace_fl, fragmentList.get(0))
                .add(R.id.replace_fl, fragmentList.get(1))
                .add(R.id.replace_fl, fragmentList.get(2))
                .add(R.id.replace_fl, fragmentList.get(3))
                .add(R.id.replace_fl, fragmentList.get(4))
                .show(fragmentList.get(0))
                .hide(fragmentList.get(1))
                .hide(fragmentList.get(2))
                .hide(fragmentList.get(3))
                .hide(fragmentList.get(4))
                .commit();



    }
    //当fragment改变的时候,记录位置
    private void changeFragment(int pos) {
        manager.beginTransaction()
                .hide(fragmentList.get(0))
                .hide(fragmentList.get(1))
                .hide(fragmentList.get(2))
                .hide(fragmentList.get(3))
                .hide(fragmentList.get(4))
                .show(fragmentList.get(pos))
                .commit();
    }



    @Override
    public void onClick(View v) {
        //  transaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.recommend_rdo:
                changeFragment(0);
                break;
            case R.id.forum_rdo:
                changeFragment(1);
                break;
            case R.id.look_auto_rdo:
                changeFragment(2);
                break;
            case R.id.discover_rdo:
                changeFragment(3);
                break;
            case R.id.mine_rdo:
                changeFragment(4);

                break;
            case R.id.close_drawer:
                drawerAll.closeDrawer(Gravity.RIGHT);
                break;
            default:
                break;
        }
        // transaction.commit();
    }
    @Subscribe
    public void getEvent(DataBean dataBean) {
        // drawerAll.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        transaction = getSupportFragmentManager().beginTransaction();
        drawerAll.openDrawer(Gravity.RIGHT);
        switch (dataBean.getType()) {
            case 1:
                titleTv.setText("车系论坛");
                transaction.replace(R.id.drawer_replace, new SeriesFragment());
                break;
            case 2:
                titleTv.setText("地区论坛");
                transaction.replace(R.id.drawer_replace, new AreaFragment());
                break;
            case 3:
                titleTv.setText("主题论坛");
                transaction.replace(R.id.drawer_replace, new ThemeFragment());
                break;
        }
        transaction.commit();
    }


    // 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
    private void init(){
        JPushInterface.init(getApplicationContext());
    }


    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }


    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!ExampleUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }

            }
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return super.onKeyDown(keyCode, event);

    }


    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, "再按一次退出汽车之家", Toast.LENGTH_SHORT).show();
            //使用Handler延时3秒发送(postDelayed)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 3000);

        } else {
            finish();
            System.exit(0);
        }
    }


    // 当按下返回键时  会调用此方法
    @Override
    public void onBackPressed() {
        //判断是否退出
        if (isExit == false) {
            isExit = true;
            Toast.makeText(this, "再按一次退出汽车之家", Toast.LENGTH_SHORT).show();
            //通过TimerTask延时2秒发送  如果在2秒之内点击 则不走本次判断 直接执行else
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            };
            //启动任务
            timer.schedule(timerTask, 2000);

        } else {
            //退出
            finish();
            timer.cancel();
            System.exit(0);
        }
    }
}
