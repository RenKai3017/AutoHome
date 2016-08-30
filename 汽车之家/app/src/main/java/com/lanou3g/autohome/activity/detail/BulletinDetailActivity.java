package com.lanou3g.autohome.activity.detail;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.autohome.bean.BulletinDetailBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.adapter.BulletinDetailAdapter;
import com.lanou3g.autohome.base.BaseActivity;
import com.lanou3g.autohome.volleyrequest.VolleySingleQueue;
import com.squareup.picasso.Picasso;


public class BulletinDetailActivity extends BaseActivity {

    private ListView listView;
    private BulletinDetailAdapter bulletinDetailAdapter;
    private ImageView backIv;
    private TextView  title,  authorName,number;

    //绑定布局
    @Override
    protected int getLayout() {
        return R.layout.activity_bulletin_detail;
    }

    //初始化组件
    @Override
    protected void initView() {
        listView =findView(R.id.activity_bulletin_detail_lv);
        bulletinDetailAdapter = new BulletinDetailAdapter(this);
        //给listView添加头布局
        View headView = LayoutInflater.from(this).inflate(R.layout.item_bulletin_detail_head, null);
        listView.addHeaderView(headView);
        //初始化组件
        backIv = findView(R.id.activity_bulletin_detail_iv);
        title = findView(R.id.activity_bulletin_detail_title);
        authorName = findView(R.id.activity_bulletin_detail_name);
        number = findView(R.id.activity_bulletin_detail_number);
    }

    @Override
    protected void initData() {

        //通过intent来获取快报页面传过来的id
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        //用id来拼接详情的网址
        String url = "http://cont.app.autohome.com.cn/autov5.0.0/content/News/fastnewscontent-n" + id + "-lastid0-o1.json";
        VolleySingleQueue.addRequest(url, BulletinDetailBean.class,
                new Response.Listener<BulletinDetailBean>() {
                    @Override
                    public void onResponse(BulletinDetailBean response) {
                        bulletinDetailAdapter.setBulletinDetailBean(response);
                        //设置数据
                        getIntentData(backIv, response.getResult().getNewsdata().getImg());
                        title.setText(response.getResult().getNewsdata().getTitle());
                        authorName.setText(response.getResult().getNewsdata().getNewsauthor());
                        number.setText(response.getResult().getNewsdata().getReviewcount() + "评论");

                        listView.setAdapter(bulletinDetailAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
    }

    //使用毕加索来记载图片的方法
    private void getIntentData(ImageView view, String url) {
        Picasso.with(this).load(url).into(view);
    }
}
