package me.taiwei.zaku.volley.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import me.taiwei.zaku.volley.utils.GsonUtils;
import me.taiwei.zaku.volley.utils.ResponseListener;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by taiwei on 15/9/17.
 */
public class GsonRequest<T> extends Request<T> {

    private final ResponseListener mListener;

    private final Class<T> mClazz;

    private Map<String, String> mHeaders;

    public GsonRequest(int method, String url, ResponseListener listener, Class<T> clazz, Map<String, String> headers) {
        super(method, url, listener);
        mListener = listener;
        mClazz = clazz;
        mHeaders = headers;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeaders != null ? mHeaders : super.getHeaders();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json =  new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(GsonUtils.getInstance().fromJson(json, mClazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
