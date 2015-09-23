package me.taiwei.zaku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import me.taiwei.zaku.retrofit.RetrofitActivity;
import me.taiwei.zaku.volley.VolleyActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onRetrofit(View v) {
        startActivity(new Intent(this, RetrofitActivity.class));
    }

    public void onVolley(View v) {
        startActivity(new Intent(this, VolleyActivity.class));
    }
}
