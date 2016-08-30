package com.lanou3g.autohome.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lanou3g.autohome.R;

/**
 * Created by dllo on 16/8/8.
 */
public class CarouselAty extends AppCompatActivity {
    private WebView webCarousel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_carousel);
        webCarousel = (WebView) findViewById(R.id.web_carousel);
        String url = getIntent().getStringExtra("url");
        WebSettings webSettings = webCarousel.getSettings();
        webSettings.setAllowContentAccess(false);
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptEnabled(true);
        webCarousel.loadUrl(url);
        webCarousel.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
