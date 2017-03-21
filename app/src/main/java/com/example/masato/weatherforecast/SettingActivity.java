package com.example.masato.weatherforecast;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.masato.weatherforecast.databinding.ActivitySettingBinding;

public class SettingActivity extends AppCompatActivity {

    ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);

        final ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                        getResources().getStringArray(R.array.prefectures));

        binding.listView.setAdapter(arrayAdapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                String prefectureName = arrayAdapter.getItem(position);
                startLocaleSetting(prefectureName, position);
            }
        });


    }

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        return intent;
    }

    public void startLocaleSetting(String prefectureName, int prefectureNum) {
        Intent intent = LocaleSettingActivity.createIntent(this, prefectureName, prefectureNum);
        startActivity(intent);
    }

}
