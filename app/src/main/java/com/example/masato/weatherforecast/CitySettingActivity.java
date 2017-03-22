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

import com.example.masato.weatherforecast.databinding.ActivityLocaleSettingBinding;
import com.example.masato.weatherforecast.model.Prefecture.Prefecture;

import java.util.ArrayList;
import java.util.List;

public class CitySettingActivity extends AppCompatActivity {

    public static final String PREFECTURE_NUM = "prefecture_num";

    ActivityLocaleSettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locale_setting);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_locale_setting);

        Intent intent = getIntent();
        int prefectureNum = intent.getIntExtra(PREFECTURE_NUM, 0);

        Prefecture prefecture = PrefectureHolder
                .get(getApplicationContext())
                .get(prefectureNum);

        final String prefectureName = prefecture.getPref();
        final List<String> cityNames = prefecture.getCityNames();
        final List<String> cityIds = prefecture.getCityIds();

        final ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cityNames);

        final SharedPreferences sharedPreferences =
                getSharedPreferences("select_city", Context.MODE_PRIVATE);

        binding.listView.setAdapter(arrayAdapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                String selecetdId = cityIds.get(position);
                String selectedName = cityNames.get(position);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("id", selecetdId);
                editor.putString("name", selectedName);
                editor.apply();

                binding.textView.setText("設定場所: " + selectedName);
            }
        });

        String cityName = sharedPreferences.getString("name", "東京");
        binding.textView.setText("設定場所: " + cityName);

    }

    public static Intent createIntent(Context context, int prefectureNum) {
        Intent intent = new Intent(context, CitySettingActivity.class);
        intent.putExtra(PREFECTURE_NUM, prefectureNum);
        return intent;
    }
}
