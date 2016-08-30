package com.lanou3g.autohome.adapter.findcar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.autohome.bean.findcar.AllBrandBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.base.AutoApplication;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/8/6.
 */
public class BrandAdapter extends BaseExpandableListAdapter {
    private AllBrandBean allBrandBean;
    private LayoutInflater inflater;

    public BrandAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setAllBrandBean(AllBrandBean allBrandBean) {
        this.allBrandBean = allBrandBean;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return allBrandBean.getResult().getBrandlist() == null ? 0 :allBrandBean.getResult().getBrandlist().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return allBrandBean.getResult().getBrandlist().get(groupPosition).getList() == null ? 0 : allBrandBean.getResult().getBrandlist().get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return allBrandBean.getResult().getBrandlist().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return allBrandBean.getResult().getBrandlist().get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        FatherHolder fatherHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_expandable_father, parent, false);
            fatherHolder = new FatherHolder(convertView);
            convertView.setTag(fatherHolder);
        } else {
            fatherHolder = (FatherHolder) convertView.getTag();
        }
        fatherHolder.letterTv.setText(allBrandBean.getResult().getBrandlist().get(groupPosition).getLetter());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_expandable_child, parent, false);
            childHolder = new ChildHolder(convertView);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        childHolder.nameTv.setText(allBrandBean.getResult().getBrandlist().get(groupPosition).getList().
                get(childPosition).getName());
        Picasso.with(AutoApplication.getContext()).load(allBrandBean.getResult().getBrandlist().get(groupPosition).getList().
                get(childPosition).getImgurl()).into(childHolder.childIv);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }



    class FatherHolder {
        TextView letterTv;
        public FatherHolder(View fatherView) {
            letterTv = (TextView) fatherView.findViewById(R.id.tv_expandable_letter);
        }
    }

    class ChildHolder {
        ImageView childIv;
        TextView nameTv;
        public ChildHolder(View childView) {
            childIv = (ImageView) childView.findViewById(R.id.iv_child);
            nameTv = (TextView) childView.findViewById(R.id.tv_expandable_name);
        }
    }
}
