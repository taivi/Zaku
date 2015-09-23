package me.taiwei.zaku.volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import me.taiwei.zaku.R;
import me.taiwei.zaku.model.HotTopicModel;
import me.taiwei.zaku.model.UserModel;
import me.taiwei.zaku.volley.api.V2exApi;
import me.taiwei.zaku.volley.utils.ResponseListener;
import me.taiwei.zaku.volley.utils.VolleyManager;

/**
 * Created by taiwei on 15/9/22.
 */
public class VolleyActivity extends AppCompatActivity {

    long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        VolleyManager.initialize(this);

        startTime = System.currentTimeMillis();

//        testObjectData();
        testListData();
    }

    private void testObjectData() {
        V2exApi.getUser("Livid", new ResponseListener<UserModel>() {
            @Override
            public void onResponse(UserModel response) {

                showCostTime();

                ((TextView) findViewById(R.id.text1)).setText(String.valueOf(response.getId()));
                ((TextView) findViewById(R.id.text2)).setText(response.getUsername());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        });
    }

    private void testListData() {

        V2exApi.getHotTopics(new ResponseListener<HotTopicModel[]>() {

            @Override
            public void onResponse(HotTopicModel[] response) {

                showCostTime();

                ((TextView) findViewById(R.id.text1)).setText(String.valueOf(response[0].getId()));
                ((TextView) findViewById(R.id.text2)).setText(response[0].getTitle());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        });
    }

    private void testMultiData() {
        for(int i = 0; i < 10; i++){
            V2exApi.getUser("Livid", new ResponseListener<HotTopicModel[]>() {

                @Override
                public void onResponse(HotTopicModel[] response) {

                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }
    }

    private void showCostTime() {
        long endTime = System.currentTimeMillis();
        Toast.makeText(VolleyActivity.this, String.valueOf(endTime - startTime), Toast.LENGTH_LONG).show();
    }
}
