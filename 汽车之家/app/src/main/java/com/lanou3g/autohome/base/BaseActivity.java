package com.lanou3g.autohome.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 16/8/1.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        initData();
    }

    protected abstract int getLayout();

    protected abstract void initView();

    protected abstract void initData();

   public <T extends View> T  findView(int id) {
       return (T) findViewById(id);
   }
}
