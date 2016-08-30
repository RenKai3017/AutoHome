package com.lanou3g.autohome.bean.forum;

import java.util.List;

/**
 * Created by dllo on 16/8/12.
 */
public class CityBean {

    /**
     * returncode : 0
     * message : ok
     * result : {"rowcount":0,"timestamp":635816236393915048,"list":[{"bbsid":100001,"bbsname":"安徽","bbstype":"a","firstletter":"A"},{"bbsid":100034,"bbsname":"澳门","bbstype":"a","firstletter":"A"},{"bbsid":100002,"bbsname":"北京","bbstype":"a","firstletter":"B"},{"bbsid":100003,"bbsname":"重庆","bbstype":"a","firstletter":"C"},{"bbsid":100004,"bbsname":"福建","bbstype":"a","firstletter":"F"},{"bbsid":100005,"bbsname":"甘肃","bbstype":"a","firstletter":"G"},{"bbsid":100006,"bbsname":"广东","bbstype":"a","firstletter":"G"},{"bbsid":100007,"bbsname":"广西","bbstype":"a","firstletter":"G"},{"bbsid":100008,"bbsname":"贵州","bbstype":"a","firstletter":"G"},{"bbsid":100009,"bbsname":"海南","bbstype":"a","firstletter":"H"},{"bbsid":100010,"bbsname":"河北","bbstype":"a","firstletter":"H"},{"bbsid":100011,"bbsname":"黑龙江","bbstype":"a","firstletter":"H"},{"bbsid":100012,"bbsname":"河南","bbstype":"a","firstletter":"H"},{"bbsid":100013,"bbsname":"湖北","bbstype":"a","firstletter":"H"},{"bbsid":100014,"bbsname":"湖南","bbstype":"a","firstletter":"H"},{"bbsid":100099,"bbsname":"海外","bbstype":"a","firstletter":"H"},{"bbsid":100016,"bbsname":"江苏","bbstype":"a","firstletter":"J"},{"bbsid":100017,"bbsname":"江西","bbstype":"a","firstletter":"J"},{"bbsid":100018,"bbsname":"吉林","bbstype":"a","firstletter":"J"},{"bbsid":100019,"bbsname":"辽宁","bbstype":"a","firstletter":"L"},{"bbsid":100015,"bbsname":"内蒙古","bbstype":"a","firstletter":"N"},{"bbsid":100020,"bbsname":"宁夏","bbstype":"a","firstletter":"N"},{"bbsid":100021,"bbsname":"青海","bbstype":"a","firstletter":"Q"},{"bbsid":100022,"bbsname":"山西","bbstype":"a","firstletter":"S"},{"bbsid":100023,"bbsname":"山东","bbstype":"a","firstletter":"S"},{"bbsid":100024,"bbsname":"上海","bbstype":"a","firstletter":"S"},{"bbsid":100025,"bbsname":"四川","bbstype":"a","firstletter":"S"},{"bbsid":100031,"bbsname":"陕西","bbstype":"a","firstletter":"S"},{"bbsid":100026,"bbsname":"天津","bbstype":"a","firstletter":"T"},{"bbsid":100032,"bbsname":"台湾","bbstype":"a","firstletter":"T"},{"bbsid":100027,"bbsname":"西藏","bbstype":"a","firstletter":"X"},{"bbsid":100028,"bbsname":"新疆","bbstype":"a","firstletter":"X"},{"bbsid":100033,"bbsname":"香港","bbstype":"a","firstletter":"X"},{"bbsid":100029,"bbsname":"云南","bbstype":"a","firstletter":"Y"},{"bbsid":100030,"bbsname":"浙江","bbstype":"a","firstletter":"Z"}]}
     */

    private int returncode;
    private String message;
    /**
     * rowcount : 0
     * timestamp : 635816236393915048
     * list : [{"bbsid":100001,"bbsname":"安徽","bbstype":"a","firstletter":"A"},{"bbsid":100034,"bbsname":"澳门","bbstype":"a","firstletter":"A"},{"bbsid":100002,"bbsname":"北京","bbstype":"a","firstletter":"B"},{"bbsid":100003,"bbsname":"重庆","bbstype":"a","firstletter":"C"},{"bbsid":100004,"bbsname":"福建","bbstype":"a","firstletter":"F"},{"bbsid":100005,"bbsname":"甘肃","bbstype":"a","firstletter":"G"},{"bbsid":100006,"bbsname":"广东","bbstype":"a","firstletter":"G"},{"bbsid":100007,"bbsname":"广西","bbstype":"a","firstletter":"G"},{"bbsid":100008,"bbsname":"贵州","bbstype":"a","firstletter":"G"},{"bbsid":100009,"bbsname":"海南","bbstype":"a","firstletter":"H"},{"bbsid":100010,"bbsname":"河北","bbstype":"a","firstletter":"H"},{"bbsid":100011,"bbsname":"黑龙江","bbstype":"a","firstletter":"H"},{"bbsid":100012,"bbsname":"河南","bbstype":"a","firstletter":"H"},{"bbsid":100013,"bbsname":"湖北","bbstype":"a","firstletter":"H"},{"bbsid":100014,"bbsname":"湖南","bbstype":"a","firstletter":"H"},{"bbsid":100099,"bbsname":"海外","bbstype":"a","firstletter":"H"},{"bbsid":100016,"bbsname":"江苏","bbstype":"a","firstletter":"J"},{"bbsid":100017,"bbsname":"江西","bbstype":"a","firstletter":"J"},{"bbsid":100018,"bbsname":"吉林","bbstype":"a","firstletter":"J"},{"bbsid":100019,"bbsname":"辽宁","bbstype":"a","firstletter":"L"},{"bbsid":100015,"bbsname":"内蒙古","bbstype":"a","firstletter":"N"},{"bbsid":100020,"bbsname":"宁夏","bbstype":"a","firstletter":"N"},{"bbsid":100021,"bbsname":"青海","bbstype":"a","firstletter":"Q"},{"bbsid":100022,"bbsname":"山西","bbstype":"a","firstletter":"S"},{"bbsid":100023,"bbsname":"山东","bbstype":"a","firstletter":"S"},{"bbsid":100024,"bbsname":"上海","bbstype":"a","firstletter":"S"},{"bbsid":100025,"bbsname":"四川","bbstype":"a","firstletter":"S"},{"bbsid":100031,"bbsname":"陕西","bbstype":"a","firstletter":"S"},{"bbsid":100026,"bbsname":"天津","bbstype":"a","firstletter":"T"},{"bbsid":100032,"bbsname":"台湾","bbstype":"a","firstletter":"T"},{"bbsid":100027,"bbsname":"西藏","bbstype":"a","firstletter":"X"},{"bbsid":100028,"bbsname":"新疆","bbstype":"a","firstletter":"X"},{"bbsid":100033,"bbsname":"香港","bbstype":"a","firstletter":"X"},{"bbsid":100029,"bbsname":"云南","bbstype":"a","firstletter":"Y"},{"bbsid":100030,"bbsname":"浙江","bbstype":"a","firstletter":"Z"}]
     */

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
        private int rowcount;
        private long timestamp;
        /**
         * bbsid : 100001
         * bbsname : 安徽
         * bbstype : a
         * firstletter : A
         */

        private List<ListBean> list;

        public int getRowcount() {
            return rowcount;
        }

        public void setRowcount(int rowcount) {
            this.rowcount = rowcount;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int bbsid;
            private String bbsname;
            private String bbstype;
            private String firstletter;

            public int getBbsid() {
                return bbsid;
            }

            public void setBbsid(int bbsid) {
                this.bbsid = bbsid;
            }

            public String getBbsname() {
                return bbsname;
            }

            public void setBbsname(String bbsname) {
                this.bbsname = bbsname;
            }

            public String getBbstype() {
                return bbstype;
            }

            public void setBbstype(String bbstype) {
                this.bbstype = bbstype;
            }

            public String getFirstletter() {
                return firstletter;
            }

            public void setFirstletter(String firstletter) {
                this.firstletter = firstletter;
            }
        }
    }
}
