package me.taiwei.zaku.volley.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

/**
 * Created by taiwei on 15/9/17.
 */
public class GsonUtils {

    private static GsonUtils sInstance;
    private Gson mGson;

    public static GsonUtils getInstance() {
        if (sInstance == null) {
            synchronized (GsonUtils.class) {
                if (sInstance == null) {
                    sInstance = new GsonUtils();
                }
            }
        }
        return sInstance;
    }

    private GsonUtils() {
        mGson = new Gson();
    }

    public String toJson(Object data) {
        if (data == null) {
            return "";
        }
        return mGson.toJson(data);
    }

    public <T> T fromJson(String str, Class<T> clazz) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        T data = null;
        try {
            data = mGson.fromJson(str, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
