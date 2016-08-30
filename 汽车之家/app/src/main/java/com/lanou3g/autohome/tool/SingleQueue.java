package com.lanou3g.autohome.tool;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by dllo on 16/8/3.
 */
public class SingleQueue {
    private static SingleQueue singleQueue;

    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    public ImageLoader getImageLoader() {
        return imageLoader;
    }
    //获取请求队列的对象
    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public static SingleQueue getSingleQueue(Context context) {
        if (singleQueue == null) {
            synchronized (SingleQueue.class) {
                if (singleQueue == null) {
                    singleQueue = new SingleQueue(context);
                }
            }
        }
        return singleQueue;
    }

    private SingleQueue(Context context) {
        requestQueue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(requestQueue,new MemoryCache());
    }

    public <T> void addToRequest(Request<T> request) {
        getRequestQueue().add(request);
    }
}
