package com.lanou3g.autohome.bean.discover;

import java.util.List;

/**
 * Created by dllo on 16/8/8.
 */
public class CarouselBean {

    /**
     * returncode : 0
     * message :
     * result : {"list":[{"id":8348,"title":"805","shorttitle":"悦动","url":"http://m.mall.autohome.com.cn/topic/2016/8/xydtg/#pvareaid=104735","imgurl":"http://app2.autoimg.cn/appdfs/g17/M0D/E9/C2/autohomecar__wKgH51ekPHKATr_YAAI6Bm8N5bg243.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0},{"id":8345,"title":"805","shorttitle":"传祺活动","url":"http://m.mall.autohome.com.cn/topic/2016/8/ga3s/#pvareaid=104735","imgurl":"http://app2.autoimg.cn/appdfs/g5/M10/EE/24/autohomecar__wKgHzFekN6uAF24iAAJZyDxsVVA776.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0},{"id":8379,"title":"8月8日分期推广","shorttitle":"SP专题","url":"http://j.autohome.com.cn/loan/loan/activitySp?pvareaid=106574&type=2","imgurl":"http://app2.autoimg.cn/appdfs/g14/M08/ED/7D/autohomecar__wKgH1VekWmKAKKKDAAEw7r6QBio904.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0},{"id":8346,"title":"805","shorttitle":"创新活动组七夕","url":"http://m.mall.autohome.com.cn/topic/2016/8/lovedate/#pvareaid=104735","imgurl":"http://app2.autoimg.cn/appdfs/g22/M06/CE/CF/autohomecar__wKjBwVekN4-AZtkbAAJekv1-BOE348.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0},{"id":8350,"title":"805","shorttitle":"东南DX7","url":"http://m.mall.autohome.com.cn/topic/2016/7/dx7/index.html?pvareaid=104735","imgurl":"http://app2.autoimg.cn/appdfs/g21/M0E/CD/A2/autohomecar__wKjBwlekPOyAG1acAAL5GwsTgqg814.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0}]}
     */

    private int returncode;
    private String message;
    private ResultBean result;

    public int getReturncode() {
        return returncode;
    }

    public void setReturncode(int returncode) {
        this.returncode = returncode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 8348
         * title : 805
         * shorttitle : 悦动
         * url : http://m.mall.autohome.com.cn/topic/2016/8/xydtg/#pvareaid=104735
         * imgurl : http://app2.autoimg.cn/appdfs/g17/M0D/E9/C2/autohomecar__wKgH51ekPHKATr_YAAI6Bm8N5bg243.jpg
         * urlscheme :
         * type : 2
         * appicon :
         * siteindex : 0
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int id;
            private String title;
            private String shorttitle;
            private String url;
            private String imgurl;
            private String urlscheme;
            private int type;
            private String appicon;
            private int siteindex;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getShorttitle() {
                return shorttitle;
            }

            public void setShorttitle(String shorttitle) {
                this.shorttitle = shorttitle;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getImgurl() {
                return imgurl;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }

            public String getUrlscheme() {
                return urlscheme;
            }

            public void setUrlscheme(String urlscheme) {
                this.urlscheme = urlscheme;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getAppicon() {
                return appicon;
            }

            public void setAppicon(String appicon) {
                this.appicon = appicon;
            }

            public int getSiteindex() {
                return siteindex;
            }

            public void setSiteindex(int siteindex) {
                this.siteindex = siteindex;
            }
        }
    }
}
