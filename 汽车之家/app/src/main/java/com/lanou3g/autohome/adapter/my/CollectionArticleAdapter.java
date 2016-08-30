package com.lanou3g.autohome.adapter.my;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.autohome.R;
import com.lanou3g.autohome.base.AutoApplication;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/8/17.
 */
public class CollectionArticleAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Collection> collectionList;
    public CollectionArticleAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setCollectionList(List<Collection> collectionList) {
        this.collectionList = collectionList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return collectionList == null ? 0 :collectionList.size();
    }

    @Override
    public Object getItem(int position) {
        return collectionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CollectionHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_collection_lv, parent, false);
            holder = new CollectionHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (CollectionHolder) convertView.getTag();
        }
        Collection collection = collectionList.get(position);
        Picasso.with(AutoApplication.getContext()).load(collection.getImageUrl()).into(holder.collectionIv);
        holder.collectionTitle.setText(collection.getTitle());
        return convertView;
    }

    class CollectionHolder {
        TextView collectionTitle;
        ImageView collectionIv;
        public CollectionHolder(View itemView) {
            collectionTitle = (TextView) itemView.findViewById(R.id.collection_title);
            collectionIv = (ImageView) itemView.findViewById(R.id.collection_iv);
        }
    }

}
