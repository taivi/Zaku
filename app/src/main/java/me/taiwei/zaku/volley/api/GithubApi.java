package me.taiwei.zaku.volley.api;

import me.taiwei.zaku.volley.ResponseListener;
import me.taiwei.zaku.volley.VolleyManager;
import me.taiwei.zaku.volley.model.GithubUserModel;
import me.taiwei.zaku.volley.request.GetGsonRequest;

/**
 * Created by taiwei on 15/9/22.
 */
public class GithubApi {

    private static final String url = "https://api.github.com/users/taivi";

    public static void getUser(ResponseListener listener) {
        GetGsonRequest request = new GetGsonRequest(url, listener, GithubUserModel.class);
        VolleyManager.getInstance().getRequestQueue().add(request);
    }
}
