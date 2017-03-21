package com.example.masato.weatherforecast;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather_forecast);

        binding.placeTitle.setText("横浜の天気");

        TabLayout tabLayout = binding.tabs;
        ViewPager pager = binding.pager;
        final String[] pageTitle = {"2 days", "1 week"};

        FragmentPagerAdapter adapter  = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return DaysForecastFragment.newInstance(position+1);
                    case 1:
                        return WeekForecastFragment.newInstance(position+1);
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
