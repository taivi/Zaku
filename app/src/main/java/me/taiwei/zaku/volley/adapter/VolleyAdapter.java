package me.taiwei.zaku.volley.adapter;

import android.content.Context;

import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;

import java.io.File;

/**
 * Created by taiwei on 15/10/9.
 */
public class VolleyAdapter {

    private final static String DEFAULT_CACHE_DIR = "volley";

    private static RequestQueue mRequestQueue;

    private final Context context;
    private final String cacheDir;
    private final Network network;

    private VolleyAdapter(Builder builder) {
        context = builder.context;
        cacheDir = builder.cacheDir;
        network = builder.network;
    }

    public void createRequestQueue() {
        if(mRequestQueue != null){
//            throw new IllegalAccessError("RequestQueue has been initialized");
            return;
        }
        File cacheFile = new File(context.getCacheDir(), cacheDir);
        mRequestQueue = new RequestQueue(new DiskBasedCache(cacheFile), network);
        mRequestQueue.start();
    }

    public static RequestQueue getRequestQueue() {
        if(mRequestQueue == null) {
            throw new NullPointerException("RequestQueue has not been initialized");
        }
        return mRequestQueue;
    }

    public static void addRequest(Request<?> request) {
        if(mRequestQueue == null) {
            throw new NullPointerException("RequestQueue has not been initialized");
        }
        mRequestQueue.add(request);
    }

    public static class Builder {
        private Context context;
        private String cacheDir;
        private HttpStack httpStack;
        private Network network;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setCacheDir(String cacheDir) {
            this.cacheDir = cacheDir;
            return this;
        }

        public Builder setHttpStack(HttpStack httpStack) {
            this.httpStack = httpStack;
            return this;
        }

        public Builder setNetwork(Network network) {
            this.network = network;
            return this;
        }

        public VolleyAdapter build() {
            ensureSaneDefaults();
            return new VolleyAdapter(this);
        }

        private void ensureSaneDefaults() {
            if (cacheDir == null) {
                cacheDir = DEFAULT_CACHE_DIR;
            }
            if (httpStack == null) {
                httpStack = new HurlStack();
            }
            if (network == null) {
                network = new BasicNetwork(httpStack);
            }
        }
    }
}
