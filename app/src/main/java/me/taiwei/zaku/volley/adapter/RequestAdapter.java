package me.taiwei.zaku.volley.adapter;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import me.taiwei.zaku.volley.utils.GsonUtils;

/**
 * Created by taiwei on 15/10/9.
 */
public class RequestAdapter<T> extends Request<T> {

    private Response.Listener successListener;
    private Map<String, String> headers;
    private Map<String, String> params;
    private Class<T> clazz;

    private RequestAdapter(Builder builder) {
        this(builder.method, builder.url, builder.errorListener);
        successListener = builder.successListener;
        headers = builder.headers;
        params = builder.params;
        clazz = builder.clazz;
    }

    private RequestAdapter(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json =  new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(GsonUtils.getInstance().fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        successListener.onResponse(response);
    }

    public static class Builder<T> {
        private int method;
        private String url;
        private Response.Listener successListener;
        private Response.ErrorListener errorListener;
        private Map<String, String> headers;
        private Map<String, String> params;
        private Class<T> clazz;

        public Builder setMethod(int method) {
            this.method = method;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setSuccessListener(Response.Listener successListener) {
            this.successListener = successListener;
            return this;
        }

        public Builder setErrorListener(Response.ErrorListener errorListener) {
            this.errorListener = errorListener;
            return this;
        }

        public Builder setHeaders(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public Builder setParams(Map<String, String> params) {
            this.params = params;
            return this;
        }

        public Builder setClass(Class<T> clazz) {
            this.clazz = clazz;
            return this;
        }

        public RequestAdapter build() {
            if(url == null){
                throw new IllegalArgumentException("url cannot be null");
            }
            if(clazz == null){
                throw new IllegalArgumentException("class cannot be null");
            }
            ensureSaneDefaults();
            return new RequestAdapter(this);
        }

        private void ensureSaneDefaults() {
            if(method < 0 || method > 7){
                method = 0;
            }
            if(successListener == null){
                successListener = new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {

                    }
                };
            }
            if(errorListener == null){
                errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                };
            }
        }
    }
}
