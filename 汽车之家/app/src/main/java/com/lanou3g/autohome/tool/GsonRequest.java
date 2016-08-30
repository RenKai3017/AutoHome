package com.lanou3g.autohome.tool;

import android.preference.PreferenceActivity.Header;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by dllo on 16/8/3.
 */
public class GsonRequest<T> extends Request<T> {
    private Listener<T> mListener;
    private Gson mGson;
    private Class<T> mClass;

    public GsonRequest(int method, String url, ErrorListener listener, Listener<T> mListener, Class<T> mClass) {
        super(method, url, listener);
        this.mListener = mListener;
        this.mClass = mClass;
        this.mGson = new Gson();
    }

    public GsonRequest(String url, ErrorListener listener, Listener<T> mListener, Class<T> mClass) {
        this(Method.GET, url, listener, mListener, mClass);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(mGson.fromJson(jsonString,mClass),HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
           return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);

    }
}
