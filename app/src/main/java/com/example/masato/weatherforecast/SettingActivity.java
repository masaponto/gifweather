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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);

        ArrayList<Prefecture> prefectures = loadPrefecture();

        //final ArrayAdapter<String> arrayAdapter =
        //new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
        //getResources().getStringArray(R.array.prefectures));

        final ArrayAdapter<Prefecture> arrayAdapter =
                new PrefectureArrayAdapter(this, R.layout.setting, prefectures);

        binding.listView.setAdapter(arrayAdapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Prefecture pref = arrayAdapter.getItem(position);
                String prefectureName = pref.getPref();
                startCitySetting(prefectureName, position, pref.getCityNames(), pref.getCityIds());
            }
        });


    }

    private ArrayList<Prefecture> loadPrefecture() {
        Gson gson = new Gson();
        ArrayList<Prefecture> prefList = new ArrayList<>();
        JsonReader reader;
        InputStream inputStream = getResources().openRawResource(R.raw.prefecture);

        try {
            reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            prefList = gson.fromJson(reader, new TypeToken<ArrayList<Prefecture>>(){}.getType());

        } catch (UnsupportedEncodingException e ) {
            e.printStackTrace();
        }

        return prefList;
    }


    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        return intent;
    }

    public void startCitySetting(String prefectureName, int prefectureNum,
                                 List<String> cityNames, List<String> cityIds) {
        Intent intent = CitySettingActivity.createIntent(
                this, prefectureName, prefectureNum, cityNames, cityIds);
        startActivity(intent);
    }

}
