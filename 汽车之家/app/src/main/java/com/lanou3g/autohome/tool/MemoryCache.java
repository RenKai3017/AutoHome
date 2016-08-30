package com.lanou3g.autohome.tool;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

/**
 * Created by dllo on 16/8/3.
 */
public class MemoryCache implements ImageCache {
    //LruCache 可以用来缓存,内部帮我们写好了算法
    //可以在缓存满的时候,把最近最少使用的东西 干掉
    private LruCache<String, Bitmap> lruCache;
    public MemoryCache() {
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 1024 / 8);
        lruCache = new LruCache<String, Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }
    @Override
    public Bitmap getBitmap(String url) {
        return lruCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        lruCache.put(url,bitmap);
    }
}
