package com.lanou3g.autohome.activity.detail;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lanou3g.autohome.R;
import com.lanou3g.autohome.base.BaseActivity;

/**
 * Created by dllo on 16/8/5.
 */
public class ReuseDetailAty extends BaseActivity {
    private WebView webReuse;
    @Override
    protected int getLayout() {
        return R.layout.activity_reuse_detail;
    }

    @Override
    protected void initView() {
        webReuse = findView(R.id.web_reuse);
    }

    @Override
    protected void initData() {
        String url = getIntent().getStringExtra("detail");
        WebSettings webSettings = webReuse.getSettings();
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
        webReuse.loadUrl(url);
        webReuse.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
