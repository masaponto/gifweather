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
import android.widget.ListView;

import com.example.masato.weatherforecast.databinding.ActivityCitySettingBinding;
import com.example.masato.weatherforecast.model.Prefecture.Prefecture;

import java.util.List;

public class CitySettingActivity extends AppCompatActivity {

    public static final String PREFECTURE_NUM = "prefecture_num";

    ActivityCitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_setting);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_city_setting);

        Intent intent = getIntent();
        final int prefectureNum = intent.getIntExtra(PREFECTURE_NUM, 0);

        Prefecture prefecture = PrefectureHolder
                .get(getApplicationContext())
                .get(prefectureNum);

        final String prefectureName = prefecture.getPref();
        final List<String> cityNames = prefecture.getCityNames();
        final List<String> cityIds = prefecture.getCityIds();

        final SharedPreferences sharedPreferences =
                getSharedPreferences("select_city", Context.MODE_PRIVATE);

        //final ArrayAdapter<String> arrayAdapter =
        //new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cityNames);

        final ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, cityNames);
        binding.listView.setAdapter(arrayAdapter);
        binding.listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        int setPrefNum = sharedPreferences.getInt("prefectureNum", 16);
        if (setPrefNum == prefectureNum) {
            int cityNum = sharedPreferences.getInt("cityNum", 0);
            binding.listView.setItemChecked(cityNum, true);
        }

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                String selectedId = cityIds.get(position);
                String selectedName = cityNames.get(position);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("prefectureNum", prefectureNum);
                editor.putInt("cityNum", position);
                editor.putString("id", selectedId);
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
