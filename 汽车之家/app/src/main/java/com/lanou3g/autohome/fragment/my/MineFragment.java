package com.lanou3g.autohome.fragment.my;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.lanou3g.autohome.R;
import com.lanou3g.autohome.base.BaseFragment;
import com.lanou3g.autohome.tool.InterfaceValues;

/**
 * Created by dllo on 16/8/20.
 */
public class MineFragment extends BaseFragment {
    private FragmentTransaction transaction;
    private int loginNum;
    private LoginBroadCast loginBroadCast;
    String name;

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        loginBroadCast = new LoginBroadCast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(InterfaceValues.BROAD_LOGIN);
        context.registerReceiver(loginBroadCast, intentFilter);

    }

    @Override
    public void onStart() {
        super.onStart();

        if (loginNum == 1) {
            transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.login_fragment, new LoginAfterFragment(name), "1");

        } else {
            transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.login_fragment, new LoginBeforeFragment(), "2");
        }
        transaction.commit();
    }


    @Override
    public void onDestroy() {
        context.unregisterReceiver(loginBroadCast);
        super.onDestroy();
    }

    class LoginBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            loginNum = intent.getIntExtra("login", 0);
            name = intent.getStringExtra("name");

        }
    }

}
