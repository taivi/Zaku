package me.taiwei.zaku.race;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.squareup.okhttp.OkHttpClient;

import me.taiwei.zaku.R;
import me.taiwei.zaku.volley.VolleyManager;
import me.taiwei.zaku.volley.adapter.VolleyAdapter;
import me.taiwei.zaku.volley.api.VolleyApi;
import me.taiwei.zaku.volley.okhttp.OkHttpStack;
import me.taiwei.zaku.volley.utils.ResponseListener;

/**
 * Created by taiwei on 15/10/8.
 */
public class HttpRaceActivity extends AppCompatActivity {

    private TextView mHurlResult;
    private TextView mOkHttpResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_http);

        mHurlResult = (TextView) findViewById(R.id.hurl_result);
        mOkHttpResult = (TextView) findViewById(R.id.okhttp_result);

        //初始化OkHttp的Volley请求队列
        VolleyAdapter volleyAdapter =
                new VolleyAdapter.Builder(this)
                        .setHttpStack(new OkHttpStack(new OkHttpClient()))
                        .build();
        volleyAdapter.createRequestQueue();

        //初始化Hurl的Volley请求队列
        VolleyManager.initialize(this);
    }

    public void onRequest(View v) {
        hurlRequest();
        okhttpRequest();
    }

    private void hurlRequest() {
        final long startTime = System.currentTimeMillis();

        for(int i = 1; i < 10; i++) {
            VolleyApi.getUser(i, new ResponseListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(HttpRaceActivity.this, "hurl error", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(Object response) {
                    long endTime = System.currentTimeMillis();
                    mHurlResult.setText(String.valueOf(endTime - startTime));
                }
            });
        }

    }

    private void okhttpRequest() {
        final long startTime = System.currentTimeMillis();

        for(int i = 1; i < 10; i++) {
            VolleyApi.getUserByOkHttp(i, new ResponseListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(HttpRaceActivity.this, "okhttp error", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(Object response) {
                    long endTime = System.currentTimeMillis();
                    mOkHttpResult.setText(String.valueOf(endTime - startTime));
                }
            });
        }
    }
}
