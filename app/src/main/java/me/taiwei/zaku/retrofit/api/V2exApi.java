package me.taiwei.zaku.retrofit.api;

import java.util.List;

import me.taiwei.zaku.retrofit.model.HotTopicModel;
import me.taiwei.zaku.retrofit.model.UserModel;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by taiwei on 15/9/22.
 */
public interface V2exApi {

    @GET("/api/topics/hot.json")
    HotTopicModel getHotTopics();

    @GET("/api/topics/hot.json")
    void getHotTopics(Callback<List<HotTopicModel>> callback);

    @GET("/api/members/show.json")
    void getUser(@Query("username") String username, Callback<UserModel> callback);
}
