package me.taiwei.zaku.volley.api;

import me.taiwei.zaku.volley.ResponseListener;
import me.taiwei.zaku.volley.VolleyManager;
import me.taiwei.zaku.volley.model.HotTopicModel;
import me.taiwei.zaku.volley.request.GetGsonRequest;

/**
 * Created by taiwei on 15/9/22.
 */
public class V2exApi {

    private static final String url = "https://www.v2ex.com/api/topics/hot.json";

    public static void getHotTopics(ResponseListener listener) {
        GetGsonRequest request = new GetGsonRequest(url, listener, HotTopicModel.class);
        VolleyManager.getInstance().getRequestQueue().add(request);
    }
}
