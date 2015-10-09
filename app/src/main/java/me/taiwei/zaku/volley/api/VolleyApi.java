package me.taiwei.zaku.volley.api;

import me.taiwei.zaku.model.HotTopicModel;
import me.taiwei.zaku.model.UserModel;
import me.taiwei.zaku.volley.adapter.VolleyAdapter;
import me.taiwei.zaku.volley.utils.ResponseListener;
import me.taiwei.zaku.volley.VolleyManager;
import me.taiwei.zaku.volley.request.GetGsonRequest;

/**
 * Created by taiwei on 15/9/22.
 */
public class VolleyApi {

    private static final String url_hot_topics = "https://www.v2ex.com/api/topics/hot.json";

    private static final String url_user = "https://www.v2ex.com/api/members/show.json";

    public static void getHotTopics(ResponseListener listener) {
        GetGsonRequest request = new GetGsonRequest(url_hot_topics, listener, HotTopicModel[].class);
        VolleyManager.getInstance().getRequestQueue().add(request);
    }

    public static void getUser(int id, ResponseListener listener) {
        String url = url_user + "?id=" + id;
        GetGsonRequest request = new GetGsonRequest(url, listener, UserModel.class);
        VolleyManager.getInstance().getRequestQueue().add(request);
    }

    public static void getUserByOkHttp(int id, ResponseListener listener) {
        String url = url_user + "?id=" + id;
        GetGsonRequest request = new GetGsonRequest(url, listener, UserModel.class);
        VolleyAdapter.addRequest(request);
    }
}
