package com.lanou3g.autohome.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lanou3g.autohome.fragment.ReuseFragment;

/**
 * Created by dllo on 16/8/5.
 */
public class SelectionAdapter extends FragmentPagerAdapter {

    public SelectionAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ReuseFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }


    private String[] titles = {"全部", "媳妇当车模", "美人记", "论坛名人堂", "论坛讲师", "汽车之家十年", "精挑细选",
            "现身说法", "高端阵地", "电动车", "汇买车", "行业点评", "超级试驾员", "海外购车", "经典老车", "妹子选车",
            "优惠购车", "原创大片", "顶配风采", "改装有理", "养车有道", "首发阵营", "新车直播", "历史选题", "摩友天地",
            "蜜月之旅", "甜蜜婚礼", "摄影课堂", "车友聚会", "单车部落", "杂谈俱乐部", "华北游记", "西南游记", "东北游记",
            "西北游记", "华中游记", "华南游记", "华东游记", "港澳台游记", "海外游记", "沧海遗珠"};

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
