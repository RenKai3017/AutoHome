package com.lanou3g.autohome.fragment.findautofragment;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lanou3g.autohome.R;
import com.lanou3g.autohome.base.BaseFragment;
import com.lanou3g.autohome.tool.InterfaceValues;

/**
 * Created by dllo on 16/8/3.
 */
public class OldCarFragment extends BaseFragment {
    private WebView webOld;
    @Override
    protected int getLayout() {
        return R.layout.fragment_old_car;
    }

    @Override
    protected void initView(View view) {
        webOld = findView(R.id.web_old_car);
    }

    @Override
    protected void initData() {
        WebSettings webSettings = webOld.getSettings();
        webSettings.setBuiltInZoomControls(false);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptEnabled(true);
        webOld.loadUrl(InterfaceValues.WEB_URL);
        webOld.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
