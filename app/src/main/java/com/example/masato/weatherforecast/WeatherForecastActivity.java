package com.example.masato.weatherforecast;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.masato.weatherforecast.databinding.ActivityWeatherForecastBinding;

public class WeatherForecastActivity extends AppCompatActivity
        implements WeekForecastFragment.OnFragmentInteractionListener,
        DaysForecastFragment.OnFragmentInteractionListener {

    private ActivityWeatherForecastBinding binding;
    private SharedPreferences sharedPreferences;
    private String placeCode, cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather_forecast);

        TabLayout tabLayout = binding.tabs;
        ViewPager pager = binding.pager;
        final String[] pageTitle = getResources().getStringArray(R.array.page_title);

        sharedPreferences =
                getSharedPreferences("select_city", Context.MODE_PRIVATE);

        placeCode = sharedPreferences.getString("id", "130010");
        cityName = sharedPreferences.getString("name", "東京");
        binding.placeTitle.setText(cityName + "の天気");

        final WeekForecastFragment weekForecastFragment = WeekForecastFragment.newInstance(placeCode);
        final DaysForecastFragment daysForecastFragment = DaysForecastFragment.newInstance(placeCode);

        FragmentPagerAdapter adapter  = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return daysForecastFragment;
                    case 1:
                        return weekForecastFragment;
                        //return WeekForecastFragment.newInstance(placeCode);
                }
                return null;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return pageTitle[position];
            }

            @Override
            public int getCount() {
                return pageTitle.length;
            }
        };

        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sharedPreferences =
                getSharedPreferences("select_city", Context.MODE_PRIVATE);

        placeCode = sharedPreferences.getString("id", "130010");
        cityName = sharedPreferences.getString("name", "東京");
        binding.placeTitle.setText(cityName + "の天気");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            intentSetting();
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void intentSetting(){
        Intent intent = SettingActivity.createIntent(getApplicationContext());
        startActivity(intent);
    }
}
