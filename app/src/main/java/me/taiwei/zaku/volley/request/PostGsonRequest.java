package me.taiwei.zaku.volley.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;

import java.util.Map;

import me.taiwei.zaku.volley.utils.ResponseListener;

/**
 * Created by taiwei on 15/9/17.
 */
public class PostGsonRequest<T> extends GsonRequest<T> {

    private final Map<String, String> mParams;

    public PostGsonRequest(String url, ResponseListener listener, Class<T> clazz, Map<String, String> params) {
        this(url, listener, clazz, params, null);
    }

    public PostGsonRequest(String url, ResponseListener listener, Class<T> clazz, Map<String, String> params, Map<String, String> headers) {
        super(Request.Method.POST, url, listener, clazz, headers);
        mParams = params;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams != null ? mParams : super.getParams();
    }
}
