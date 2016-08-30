package com.lanou3g.autohome.fragment.my;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou3g.autohome.R;
import com.lanou3g.autohome.base.BaseFragment;
import com.squareup.picasso.Picasso;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/8/20.
 */
@SuppressLint("ValidFragment")
public class LoginAfterFragment extends BaseFragment implements OnClickListener {
    private TextView userNameTv;
    private RelativeLayout exitRl;
    private UserBroad userBroad;
    private String name;
    private ImageView icon;
    private Platform qq;


    public LoginAfterFragment(String name) {
        this.name = name;
    }

    public LoginAfterFragment() {
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_login_after;
    }

    @Override
    protected void initView(View view) {
        userNameTv = findView(R.id.user_name);
        icon = findView(R.id.myself_photo);
        exitRl = findView(R.id.myself_return);
        exitRl.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        qq = ShareSDK.getPlatform(QQ.NAME);
        userNameTv.setText(name);

        if (qq.isValid()) {
            Picasso.with(context).load(qq.getDb().getUserIcon()).into(icon);
            userNameTv.setText(qq.getDb().getUserName());
        }

        userBroad = new UserBroad();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("load is success");
        context.registerReceiver(userBroad, intentFilter);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myself_return:
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction =manager.beginTransaction();
                transaction.replace(R.id.login_fragment, new LoginBeforeFragment());
                transaction.commit();

                if (qq.isValid()) {
                    qq.removeAccount();
                }
                break;
        }
    }

    class UserBroad extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Picasso.with(context).load(qq.getDb().getUserIcon()).into(icon);
            userNameTv.setText(qq.getDb().getUserName());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
      context.unregisterReceiver(userBroad);
    }
}
