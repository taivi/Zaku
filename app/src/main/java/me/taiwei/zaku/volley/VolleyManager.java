package me.taiwei.zaku.volley;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;

import java.io.File;

/**
 * Created by taiwei on 15/9/22.
 */
public class VolleyManager {

    private static final String DEFAULT_CACHE_DIR = "volley";

    private static VolleyManager sInstance;

    private RequestQueue mRequestQueue;

    public static void initialize(Context context) {
        sInstance = new VolleyManager(context);
    }

    public static VolleyManager getInstance() {
        if (sInstance == null) {
            throw new NullPointerException("VolleyManager has not been initialized");
        }
        return sInstance;
    }

    private VolleyManager(Context context) {

        File cacheDir = new File(context.getCacheDir(), DEFAULT_CACHE_DIR);

        String userAgent = "volley/0";
        try {
            String packageName = context.getPackageName();
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
            userAgent = packageName + "/" + info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {

        }

        HttpStack stack = new HurlStack();
        Network network = new BasicNetwork(stack);

        mRequestQueue = new RequestQueue(new DiskBasedCache(cacheDir), network);
        mRequestQueue.start();
    }

    public RequestQueue getRequestQueue() {
        if(mRequestQueue == null){
            throw new NullPointerException("RequestQueue has not been initialized");
        }
        return mRequestQueue;
    }
}
