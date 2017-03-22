package com.example.masato.weatherforecast;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.masato.weatherforecast.databinding.ActivitySettingBinding;
import com.example.masato.weatherforecast.model.Prefecture.Prefecture;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity {

    ActivitySettingBinding binding;

    private SharedPreferences sharedPreferences;
    private String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);

        ArrayList<Prefecture> prefectures = PrefectureHolder.get(getApplicationContext());

        final ArrayAdapter<Prefecture> arrayAdapter =
                new PrefectureArrayAdapter(this, R.layout.setting, prefectures);

        binding.listView.setAdapter(arrayAdapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                startCitySetting(position);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        sharedPreferences =
                getSharedPreferences("select_city", Context.MODE_PRIVATE);
        cityName = sharedPreferences.getString("name", "東京");

        binding.textView.setText("設定場所: " + cityName);
    }


    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        return intent;
    }

    public void startCitySetting(int prefectureNum) {
        Intent intent = CitySettingActivity.createIntent(this, prefectureNum);
        startActivity(intent);
    }

}
