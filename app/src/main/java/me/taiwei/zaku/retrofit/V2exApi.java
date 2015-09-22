package me.taiwei.zaku.retrofit;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by taiwei on 15/9/22.
 */
public interface V2exApi {

    @GET("/api/topics/hot.json")
    HotTopicModel getHotTopics();

    @GET("/api/topics/hot.json")
    void getHotTopics(Callback<List<HotTopicModel>> callback);
}
