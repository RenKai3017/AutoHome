package com.lanou3g.autohome.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.autohome.MainActivity;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.base.BaseActivity;
import com.lanou3g.autohome.guide.GuideActivity;
import com.lanou3g.autohome.tool.DiskCache;
import com.lanou3g.autohome.tool.InterfaceValues;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by dllo on 16/8/23.
 */
public class WelcomeActivity extends BaseActivity {
    private ImageView bitmapIv;
    private TextView welcomeTv;
    private CountDownTimer timer;
    private WelcomeTask welcomeTask;

    @Override
    protected int getLayout() {
        SharedPreferences sharedPreferences = getSharedPreferences("welcome", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("first", true)) {
            startActivity(new Intent(this, GuideActivity.class));
            finish();
        }
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        bitmapIv = findView(R.id.bitmap_iv);
        welcomeTv = findView(R.id.welcome_tv);
        welcomeTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextPage();
            }
        });

    }

    @Override
    protected void initData() {
        welcomeTask = new WelcomeTask();
        welcomeTask.execute(InterfaceValues.WELCOME);
        timer = new CountDownTimer(100000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                welcomeTv.setText("点击跳转" + millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
               startNextPage();
            }
        }.start();
    }

    private void startNextPage() {
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        timer.cancel();
        finish();

    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        timer.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }


    class WelcomeTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            String imageUrl = params[0];
            HttpURLConnection connection = null;
            InputStream inputStream = null;
            Bitmap bitmap = null;
            DiskCache diskCache = new DiskCache(getApplicationContext());
            bitmap = diskCache.getBitmap(imageUrl);
            if (bitmap!= null) {
                return bitmap;
            }
            try {
                URL url = new URL(imageUrl);
                connection = (HttpURLConnection) url.openConnection();
                inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
                diskCache.putBitmap(imageUrl, bitmap);

                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                connection.disconnect();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            bitmapIv.setImageBitmap(bitmap);
        }
    }

}
