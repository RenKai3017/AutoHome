package com.lanou3g.autohome.fragment.my;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.lanou3g.autohome.R;
import com.lanou3g.autohome.activity.WebViewActivity;
import com.lanou3g.autohome.adapter.my.CollectionArticleAdapter;
import com.lanou3g.autohome.base.BaseFragment;

/**
 * Created by dllo on 16/8/17.
 */
public class CollectionArticleFragment extends BaseFragment {
    private ListView collectionLv;
    private CollectionArticleAdapter collectionArticleAdapter;
    @Override
    protected int getLayout() {
        return R.layout.fragment_article_collection;
    }

    @Override
    protected void initView(View view) {
        collectionLv = findView(R.id.collection_lv);
        collectionArticleAdapter = new CollectionArticleAdapter(context);

    }

    @Override
    protected void initData() {
        //从数据库取出收藏内容
        collectionArticleAdapter.setCollectionList(GreenDaoSingle.getInstance().getAll());
        collectionLv.setAdapter(collectionArticleAdapter);

        collectionLv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Collection collection = GreenDaoSingle.getInstance().getAll().get(position);
                String url = collection.getUrl();
                Long cId = collection.getId();
                String cTitle = collection.getTitle();
                String cSmallPic = collection.getImageUrl();
                Collection coll = new Collection(cId, cTitle,url, cSmallPic);

                Intent intent = new Intent();
                intent.putExtra("url", url);
                intent.putExtra("Collection",coll);
                intent.setClass(context, WebViewActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        collectionLv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Collection collection = GreenDaoSingle.getInstance().getAll().get(position);
                String url = collection.getUrl();
                Long cId = collection.getId();
                String cTitle = collection.getTitle();
                String cSmallPic = collection.getImageUrl();
                Collection coll = new Collection(cId, cTitle,url, cSmallPic);

                Intent intent = new Intent();
                intent.putExtra("url", url);
                intent.putExtra("Collection",coll);
                intent.setClass(context, WebViewActivity.class);
                startActivity(intent);
            }
        });

        collectionArticleAdapter.setCollectionList(GreenDaoSingle.getInstance().getAll());
        collectionLv.setAdapter(collectionArticleAdapter);

    }
}
