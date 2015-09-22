package me.taiwei.zaku.volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import me.taiwei.zaku.R;
import me.taiwei.zaku.volley.api.V2exApi;
import me.taiwei.zaku.volley.model.UserModel;

/**
 * Created by taiwei on 15/9/22.
 */
public class VolleyActivity extends AppCompatActivity {

    long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        VolleyManager.initialize(this);

        startTime = System.currentTimeMillis();
        V2exApi.getUser(new ResponseListener<UserModel>() {
            @Override
            public void onResponse(UserModel response) {

                long endTime = System.currentTimeMillis();
                Toast.makeText(VolleyActivity.this, String.valueOf(endTime - startTime), Toast.LENGTH_LONG).show();

                ((TextView) findViewById(R.id.id)).setText(String.valueOf(response.getId()));
                ((TextView) findViewById(R.id.title)).setText(response.getUsername());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        });
    }
}
