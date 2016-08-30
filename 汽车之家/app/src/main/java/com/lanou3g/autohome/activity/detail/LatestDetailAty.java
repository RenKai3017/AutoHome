package com.lanou3g.autohome.activity.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lanou3g.autohome.R;

/**
 * Created by dllo on 16/8/5.
 */
public class LatestDetailAty extends AppCompatActivity {
    private WebView webLatest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_detail);
       findViewById(R.id.back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webLatest = (WebView) findViewById(R.id.web_latest);
        String url = getIntent().getStringExtra("detail");
        WebSettings webSettings = webLatest.getSettings();
        webSettings.setAllowContentAccess(false);
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptEnabled(true);
        webLatest.loadUrl(url);
        webLatest.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }

    public static void startActivity(Context context, String url) {
        Intent startIntent = new Intent(context, LatestDetailAty.class);
        startIntent.putExtra("detail", url);
        context.startActivity(startIntent);
    }

}
