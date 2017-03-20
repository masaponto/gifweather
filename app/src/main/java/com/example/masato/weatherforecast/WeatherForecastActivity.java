package com.example.masato.weatherforecast;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        final String[] pageTitle = {"3 days", "1 week"};

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
}
