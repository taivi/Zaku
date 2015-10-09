package me.taiwei.zaku.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import me.taiwei.zaku.R;
import me.taiwei.zaku.model.HotTopicModel;
import me.taiwei.zaku.model.UserModel;
import me.taiwei.zaku.retrofit.api.RetrofitApi;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by taiwei on 15/9/22.
 */
public class RetrofitActivity extends AppCompatActivity {

    long startTime;

    String BASE_URL = "https://www.v2ex.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(BASE_URL).build();
        RetrofitApi v2exApi = adapter.create(RetrofitApi.class);

        startTime = System.currentTimeMillis();

        testObjectData(v2exApi);
//        testListData(v2exApi);
    }

    private void testObjectData(RetrofitApi v2exApi){
        v2exApi.getUser(1,
                new Callback<UserModel>() {
                    @Override
                    public void success(UserModel userModel, Response response) {

                        showCostTime();

                        ((TextView) findViewById(R.id.text1)).setText(String.valueOf(userModel.getId()));
                        ((TextView) findViewById(R.id.text2)).setText(userModel.getUsername());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        System.out.println(error.toString());
                    }
                });
    }

    private void testListData(RetrofitApi v2exApi){
        v2exApi.getHotTopics(new Callback<List<HotTopicModel>>() {
            @Override
            public void success(List<HotTopicModel> hotTopicModels, Response response) {

                showCostTime();

                ((TextView) findViewById(R.id.text1)).setText(String.valueOf(hotTopicModels.get(0).getId()));
                ((TextView)findViewById(R.id.text2)).setText(hotTopicModels.get(0).getTitle());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void showCostTime() {
        long endTime = System.currentTimeMillis();
        Toast.makeText(RetrofitActivity.this, String.valueOf(endTime - startTime), Toast.LENGTH_LONG).show();
    }
}
