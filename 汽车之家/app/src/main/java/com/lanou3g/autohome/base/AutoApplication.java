package com.lanou3g.autohome.base;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;
import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by dllo on 16/8/2.
 */
public class AutoApplication extends Application {
    protected static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        ShareSDK.initSDK(this);
        Bmob.initialize(this, "19e79b1233f773a67277a9edf03d6fae");
    }

    public static Context getContext() {
        return context;
    }


    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    public static DaoMaster getDaoMaster() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "AutoHome.db");
        daoMaster = new DaoMaster(helper.getWritableDatabase());
        return daoMaster;
    }

    public static DaoSession getDaoSession() {

        if (daoMaster == null) {
            if (daoSession == null) {
                //初始化
                daoSession = getDaoMaster().newSession();
            }
        }

        return daoSession;
    }
}
