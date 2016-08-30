package com.lanou3g.autohome.volleyrequest;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lanou3g.autohome.base.AutoApplication;
import com.lanou3g.autohome.tool.GsonRequest;

/**
 * Created by dllo on 16/8/8.
 */
public class VolleySingleQueue {
    private Context mContext;
    private RequestQueue queue;
    private static VolleySingleQueue volleySingleQueue = new VolleySingleQueue();

    public static VolleySingleQueue getInstance() {
        return volleySingleQueue;
    }
    private VolleySingleQueue() {
        mContext = AutoApplication.getContext();
        queue = getQueue();
    }
    //获取请求队列
    private RequestQueue getQueue() {
        if (queue == null) {
            synchronized (VolleySingleQueue.class) {
                if (queue == null) {
                    queue = Volley.newRequestQueue(mContext);
                }
            }
        }
        return queue;
    }
    //添加最基本的请求
    public <T> void _addRequest(Request<T> request) {
        queue.add(request);
    }

    public <T> void _addRequest(Request<T> request, Object tag) {
        request.setTag(tag);
        _addRequest(request);
    }
    //添加StringRequest请求
    public void _addRequest(String url, Listener<String> listener, ErrorListener errorListener) {
        StringRequest stringRequest = new StringRequest(url, listener, errorListener);
        _addRequest(stringRequest);
    }
    //添加自定义请求
    public<T> void _addRequest(String url, Class<T> mClass, Listener<T> listener, ErrorListener errorListener) {
        GsonRequest<T> gsonRequest = new GsonRequest<T>(Method.GET, url, errorListener, listener, mClass);
        _addRequest(gsonRequest);
    }

    public static <T> void addRequest(Request<T> request) {
        getInstance()._addRequest(request);
    }

    public static <T> void addRequest(Request<T> request, Object tag) {
        getInstance()._addRequest(request, tag);
    }
    //StringRequest
    public static <T> void addRequest(String url, Listener<String> listener, ErrorListener errorListener) {
        getInstance()._addRequest(url, listener, errorListener);
    }

    //GsonRequest
    public static <T> void addRequest(String url,Class<T> mClass, Listener<T> listener, ErrorListener errorListener) {
        getInstance()._addRequest(url, mClass,listener, errorListener);
    }

    public void removeRequest(Object tag) {
        queue.cancelAll(tag);
    }
}
