package me.taiwei.zaku.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import me.taiwei.zaku.R;
import me.taiwei.zaku.retrofit.api.V2exApi;
import me.taiwei.zaku.retrofit.model.UserModel;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by taiwei on 15/9/22.
 */
public class RetrofitActivity extends AppCompatActivity {

    long startTime;

    String BASE_URL_V2EX = "https://www.v2ex.com";

    String BASE_URL_GITHUB = "https://api.github.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

//        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(BASE_URL_V2EX).build();
//
//        V2exApi v2exApi = adapter.create(V2exApi.class);
//        v2exApi.getHotTopics(new Callback<List<HotTopicModel>>() {
//            @Override
//            public void success(List<HotTopicModel> hotTopicModels, Response response) {
//                HotTopicModel hotTopicModel = hotTopicModels.get(0);
//                ((TextView) findViewById(R.id.id)).setText(String.valueOf(hotTopicModel.getId()));
//                ((TextView)findViewById(R.id.title)).setText(hotTopicModel.getTitle());
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//
//            }
//        });

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(BASE_URL_V2EX).build();

        V2exApi v2exApi = adapter.create(V2exApi.class);

        startTime = System.currentTimeMillis();
        v2exApi.getUser("Livid", new Callback<UserModel>() {
            @Override
            public void success(UserModel userModel, Response response) {

                long endTime = System.currentTimeMillis();
                Toast.makeText(RetrofitActivity.this, String.valueOf(endTime - startTime), Toast.LENGTH_LONG).show();

                ((TextView) findViewById(R.id.id)).setText(String.valueOf(userModel.getId()));
                ((TextView) findViewById(R.id.title)).setText(userModel.getUsername());
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error.toString());
            }
        });
    }
}
