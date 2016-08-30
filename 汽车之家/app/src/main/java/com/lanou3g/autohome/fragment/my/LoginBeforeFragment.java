package com.lanou3g.autohome.fragment.my;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.lanou3g.autohome.bean.db.QQBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.activity.CollectionAty;
import com.lanou3g.autohome.activity.my.LoginActivity;
import com.lanou3g.autohome.base.BaseFragment;

import java.util.HashMap;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;


/**
 * Created by dllo on 16/8/2.
 */
public class LoginBeforeFragment extends BaseFragment implements OnClickListener {

    private Platform qq;
    private FragmentTransaction transaction;

    @Override
    protected int getLayout() {
        return R.layout.fragment_login_before;
    }

    @Override
    protected void initView(View view) {
        findView(R.id.my_collection).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        //登录
        findView(R.id.auto_login).setOnClickListener(this);
        //qq登录
        findView(R.id.login_qq).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_collection:
                Intent intent = new Intent(context, CollectionAty.class);
                startActivity(intent);

                break;
            //登录
            case R.id.auto_login:
                Intent loginIntent = new Intent(context, LoginActivity.class);
                startActivity(loginIntent);
                break;
            //qq登录
            case R.id.login_qq:
                qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        transaction =manager.beginTransaction();
                        transaction.replace(R.id.login_fragment, new LoginAfterFragment());
                        transaction.commit();

                        Intent intent = new Intent("load is success");
                       context.sendBroadcast(intent);

                        QQBean bean = new QQBean();
                        bean.setQQIcon(qq.getDb().getUserIcon());
                        bean.setQQId(qq.getDb().getUserId());
                        bean.setQQName(qq.getDb().getUserName());
                        bean.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null) {
                                    Toast.makeText(context, "添加数据成功:" + s, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
                qq.authorize();
                break;
        }
    }


}
