package me.taiwei.zaku.race;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import me.taiwei.zaku.R;
import me.taiwei.zaku.model.UserModel;
import me.taiwei.zaku.retrofit.api.RetrofitApi;
import me.taiwei.zaku.volley.VolleyManager;
import me.taiwei.zaku.volley.api.VolleyApi;
import me.taiwei.zaku.volley.utils.ResponseListener;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by taiwei on 15/9/22.
 *
 * 比较Volley和Retrofit通信框架的速度
 */
public class NetworkRaceActivity extends AppCompatActivity {

    private TextView mVolleySerialResult;
    private TextView mVolleyParallelResult;
    private TextView mRetrofitSerialResult;
    private TextView mRetrofitParallelResult;

    private RetrofitApi mRetrofitApi;

    private final static String BASE_URL = "https://www.v2ex.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_network);

        mVolleySerialResult = (TextView) findViewById(R.id.volley_serial_result);
        mVolleyParallelResult = (TextView) findViewById(R.id.volley_parallel_result);
        mRetrofitSerialResult = (TextView) findViewById(R.id.retrofit_serial_result);
        mRetrofitParallelResult = (TextView) findViewById(R.id.retrofit_parallel_result);

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(BASE_URL).build();
        mRetrofitApi = restAdapter.create(RetrofitApi.class);

        VolleyManager.initialize(this);
    }

    /**
     * Volley串行
     * @param v
     */
    public void onVolleySerial(View v) {
        final long startTime = System.currentTimeMillis();
    }

    /**
     * Volley并行
     * @param v
     */
    public void onVolleyParallel(View v) {
        final long startTime = System.currentTimeMillis();

        for(int i = 1; i < 10; i++) {
            VolleyApi.getUser(i, new ResponseListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(NetworkRaceActivity.this, "error", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(Object response) {
                    long endTime = System.currentTimeMillis();
                    mVolleyParallelResult.setText(String.valueOf(endTime - startTime));
                }
            });
        }
    }

    public void onRetrofitSerial(View v) {

    }

    public void onRetrofitParallel(View v) {
        final long startTime = System.currentTimeMillis();

        for(int i = 1; i < 10; i++) {
            mRetrofitApi.getUser(i, new Callback<UserModel>() {
                @Override
                public void success(UserModel userModel, Response response) {
                    long endTime = System.currentTimeMillis();
                    mRetrofitParallelResult.setText(String.valueOf(endTime - startTime));
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        }
    }
}
