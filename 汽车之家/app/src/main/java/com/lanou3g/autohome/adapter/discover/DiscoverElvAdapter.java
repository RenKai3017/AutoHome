package com.lanou3g.autohome.adapter.discover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.lanou3g.autohome.bean.discover.DiscoverBodyBean;
import com.lanou3g.autohome.R;
import com.lanou3g.autohome.base.AutoApplication;
import com.lanou3g.autohome.tool.InterfaceValues;
import com.lanou3g.autohome.volleyrequest.VolleySingleQueue;

/**
 * Created by dllo on 16/8/8.
 */
public class DiscoverElvAdapter extends BaseExpandableListAdapter {
    private DiscoverBodyBean bean;
    private LayoutInflater inflater;

    public DiscoverElvAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setBean(DiscoverBodyBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return bean.getResult().getModulelist().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return bean.getResult().getModulelist().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return bean.getResult().getModulelist().get(groupPosition).getList().get(childPosition);
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
            convertView = inflater.inflate(R.layout.item_discover_father, parent, false);
            fatherHolder = new FatherHolder(convertView);
            convertView.setTag(fatherHolder);
        } else {
            fatherHolder = (FatherHolder) convertView.getTag();
        }
        fatherHolder.fatherTitle.setText(bean.getResult().getModulelist().get(groupPosition).getTitle());
        return convertView;
    }
    private DiscoverGridAdapter gridAdapter;
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_discover_grid_child, parent, false);
        final GridView gridView = (GridView) convertView.findViewById(R.id.grid_dis);
        gridAdapter = new DiscoverGridAdapter(AutoApplication.getContext());
        VolleySingleQueue.addRequest(InterfaceValues.DISCOVER_BODY_URL, DiscoverBodyBean.class, new Listener<DiscoverBodyBean>() {
            @Override
            public void onResponse(DiscoverBodyBean response) {
                gridAdapter.setBean(response);
                gridView.setAdapter(gridAdapter);
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        gridAdapter.notifyDataSetChanged();
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class FatherHolder {
        TextView fatherTitle;

        public FatherHolder(View view) {
            fatherTitle = (TextView) view.findViewById(R.id.tv_father_title);
        }
    }


}
