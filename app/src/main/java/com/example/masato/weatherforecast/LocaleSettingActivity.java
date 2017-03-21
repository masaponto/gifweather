package com.example.masato.weatherforecast;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.masato.weatherforecast.databinding.ActivityLocaleSettingBinding;
import com.example.masato.weatherforecast.databinding.ActivitySettingBinding;

public class LocaleSettingActivity extends AppCompatActivity {

    public static final String PREFECTURE_NUM = "prefecture_num";
    public static final String PREFECTURE_NAME = "prefecture_name";

    ActivityLocaleSettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locale_setting);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_locale_setting);

        Intent intent = getIntent();
        int prefectureNum = intent.getIntExtra(PREFECTURE_NUM, 0);
        String prefectureName = intent.getStringExtra(PREFECTURE_NAME);

        binding.textView.setText("選択場所: " + prefectureName);

    }

    public static Intent createIntent(Context context, String prefectureName, int prefectureNum) {
        Intent intent = new Intent(context, LocaleSettingActivity.class);
        intent.putExtra(PREFECTURE_NAME, prefectureName);
        intent.putExtra(PREFECTURE_NUM, prefectureNum);
        return intent;
    }
}
