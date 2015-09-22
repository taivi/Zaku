package me.taiwei.zaku.volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.VolleyError;

import java.util.List;

import me.taiwei.zaku.R;
import me.taiwei.zaku.volley.api.V2exApi;
import me.taiwei.zaku.volley.model.HotTopicModel;

/**
 * Created by taiwei on 15/9/22.
 */
public class VolleyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        VolleyManager.initialize(this);

        V2exApi.getHotTopics(new ResponseListener<List<HotTopicModel>>() {
            @Override
            public void onResponse(List<HotTopicModel> response) {

                HotTopicModel hotTopicModel = response.get(0);

                ((TextView) findViewById(R.id.id)).setText(String.valueOf(hotTopicModel.getId()));
                ((TextView)findViewById(R.id.title)).setText(hotTopicModel.getTitle());
                ((TextView)findViewById(R.id.url)).setText(hotTopicModel.getUrl());
                ((TextView)findViewById(R.id.content)).setText(hotTopicModel.getContent());
                ((TextView)findViewById(R.id.replies)).setText(String.valueOf(hotTopicModel.getReplies()));
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
}
