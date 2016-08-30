package com.lanou3g.autohome.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.lanou3g.autohome.R;
import com.lanou3g.autohome.base.BaseActivity;
import com.lanou3g.autohome.tool.InterfaceValues;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dllo on 16/8/16.
 */
public class WebViewActivity extends BaseActivity implements OnClickListener {

    private WebView webView;
    String url;
    private Collection mCollection;
    Boolean flag = false;
    private ImageView collectionIv;
    private String title;
    private String image;

    @Override
    protected int getLayout() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView() {
        webView = findView(R.id.activity_web_view);
        findView(R.id.activity_web_return_tv).setOnClickListener(this);
        collectionIv = findView(R.id.activity_web_collection_iv);
        collectionIv.setOnClickListener(this);
        findView(R.id.activity_web_share_iv).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        mCollection = (Collection) intent.getSerializableExtra("Collection");
        title = intent.getStringExtra("Title");
        image = intent.getStringExtra("SmallPic");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(url);
        //删除所有,为了防止id冲突,事先将数据库里的所有数据内容清空
        // GreenDaoSingle.getInstance().deleteAll();
        //遍历数据库中的数据,查找id,当传进来的id与数据库中id向对应,就证明存储过
        for (Collection collection : GreenDaoSingle.getInstance().getAll()) {
            if (collection.getId().toString().equals(mCollection.getId().toString())) {
                collectionIv.setImageResource(R.mipmap.collection_old);
                flag = true;
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_web_return_tv:
                finish();
                break;
            case R.id.activity_web_collection_iv:
                //Toast.makeText(this, "点击", Toast.LENGTH_SHORT).show();
                if (!flag) {
                    collectionIv.setImageResource(R.mipmap.collection_old);
                    Toast.makeText(WebViewActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                    GreenDaoSingle.getInstance().insertCollection(mCollection);
                    flag = true;
                } else {
                    collectionIv.setImageResource(R.mipmap.collectionkong);
                    GreenDaoSingle.getInstance().deleteByKey(mCollection.getId());
                    Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
                    flag = false;
                }

                break;
            case R.id.activity_web_share_iv:
                showShare();
                break;
        }
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(title);
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(title);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(image);//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(InterfaceValues.LATEST_URL);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(InterfaceValues.LATEST_URL);

// 启动分享GUI
        oks.show(this);
    }
}
