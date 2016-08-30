package com.lanou3g.autohome.bean.db;

import cn.bmob.v3.BmobObject;

/**
 * Created by dllo on 16/8/22.
 */
public class QQBean extends BmobObject {
    private String QQId;
    private String QQName;
    private String QQIcon;

    public String getQQId() {
        return QQId;
    }

    public void setQQId(String QQId) {
        this.QQId = QQId;
    }

    public String getQQName() {
        return QQName;
    }

    public void setQQName(String QQName) {
        this.QQName = QQName;
    }

    public String getQQIcon() {
        return QQIcon;
    }

    public void setQQIcon(String QQIcon) {
        this.QQIcon = QQIcon;
    }
}
