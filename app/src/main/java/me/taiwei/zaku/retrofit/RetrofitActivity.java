package me.taiwei.zaku.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

import me.taiwei.zaku.R;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by taiwei on 15/9/22.
 */
public class RetrofitActivity extends AppCompatActivity {

    String BASE_URL = "https://www.v2ex.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(BASE_URL).build();

        V2exApi v2exApi = adapter.create(V2exApi.class);
        v2exApi.getHotTopics(new Callback<List<HotTopicModel>>() {

            @Override
            public void success(List<HotTopicModel> hotTopicModels, Response response) {

                HotTopicModel hotTopicModel = hotTopicModels.get(0);

                ((TextView) findViewById(R.id.id)).setText(String.valueOf(hotTopicModel.getId()));
                ((TextView)findViewById(R.id.title)).setText(hotTopicModel.getTitle());
                ((TextView)findViewById(R.id.url)).setText(hotTopicModel.getUrl());
                ((TextView)findViewById(R.id.content)).setText(hotTopicModel.getContent());
                ((TextView)findViewById(R.id.replies)).setText(String.valueOf(hotTopicModel.getReplies()));
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error.toString() + "************************");
            }
        });
    }
}
