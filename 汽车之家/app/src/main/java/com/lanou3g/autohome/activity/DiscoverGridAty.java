package com.lanou3g.autohome.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.lanou3g.autohome.R;

/**
 * Created by dllo on 16/8/9.
 */
public class DiscoverGridAty extends AppCompatActivity {
    private WebView webDisGrid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        webDisGrid = (WebView) findViewById(R.id.web_dis_grid);
        String url = getIntent().getStringExtra("url");
        webDisGrid.loadUrl(url);
        String webUrl = getIntent().getStringExtra("webUrl");
        webDisGrid.loadUrl(webUrl);
        String lvUrl = getIntent().getStringExtra("lvUrl");
        webDisGrid.loadUrl(lvUrl);
        String moreUrl = getIntent().getStringExtra("moreUrl");
        webDisGrid.loadUrl(moreUrl);
        String firstUrl = getIntent().getStringExtra("firstUrl");
        webDisGrid.loadUrl(firstUrl);
        String secondUrl = getIntent().getStringExtra("secondUrl");
        webDisGrid.loadUrl(secondUrl);
        String thirdUrl = getIntent().getStringExtra("thirdUrl");
        webDisGrid.loadUrl(thirdUrl);

    }
}
