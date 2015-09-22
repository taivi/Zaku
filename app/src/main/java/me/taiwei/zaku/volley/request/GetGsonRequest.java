package me.taiwei.zaku.volley.request;

import com.android.volley.Request;

import java.util.Map;

import me.taiwei.zaku.volley.ResponseListener;


/**
 * Created by taiwei on 15/9/17.
 */
public class GetGsonRequest<T> extends GsonRequest<T> {

    public GetGsonRequest(String url, ResponseListener listener, Class<T> clazz) {
        this(url, listener, clazz, null);
    }

    public GetGsonRequest(String url, ResponseListener listener, Class<T> clazz, Map<String, String> headers) {
        super(Request.Method.GET, url, listener, clazz, headers);
    }
}
