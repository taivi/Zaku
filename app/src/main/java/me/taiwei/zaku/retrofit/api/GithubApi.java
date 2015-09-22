package me.taiwei.zaku.retrofit.api;

import me.taiwei.zaku.retrofit.model.GithubUserModel;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by taiwei on 15/9/22.
 */
public interface GithubApi {

    @GET("/users/{user}")
    void getUser(@Path("user") String user, Callback<GithubUserModel> response);
}
