package com.example.masato.weatherforecast;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.masato.weatherforecast.databinding.ActivityLocaleSettingBinding;

import java.util.ArrayList;
import java.util.List;

public class CitySettingActivity extends AppCompatActivity {

    public static final String PREFECTURE_NUM = "prefecture_num";
    public static final String PREFECTURE_NAME = "prefecture_name";
    public static final String CITY_NAME_LIST = "city_name_list";
    public static final String CITY_ID_LIST = "city_id_list";


    ActivityLocaleSettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locale_setting);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_locale_setting);

        Intent intent = getIntent();
        int prefectureNum = intent.getIntExtra(PREFECTURE_NUM, 0);
        String prefectureName = intent.getStringExtra(PREFECTURE_NAME);
        ArrayList<String> cityNames = intent.getStringArrayListExtra(CITY_NAME_LIST);
        final ArrayList<String> cityIds = intent.getStringArrayListExtra(CITY_ID_LIST);

        final ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cityNames);

        binding.listView.setAdapter(arrayAdapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                String selecetdId = cityIds.get(position);
            }
        });


        binding.textView.setText("選択場所: " + prefectureName);

    }

    public static Intent createIntent(Context context, String prefectureName, int prefectureNum,
                                      List<String> cityNames, List<String> cityIds) {
        Intent intent = new Intent(context, CitySettingActivity.class);
        intent.putExtra(PREFECTURE_NAME, prefectureName);
        intent.putExtra(PREFECTURE_NUM, prefectureNum);
        intent.putStringArrayListExtra(CITY_NAME_LIST, (ArrayList<String>) cityNames);
        intent.putStringArrayListExtra(CITY_ID_LIST, (ArrayList<String>) cityIds);
        return intent;
    }
}
