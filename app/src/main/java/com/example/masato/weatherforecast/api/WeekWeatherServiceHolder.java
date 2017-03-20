package com.example.masato.weatherforecast.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by masato on 17/03/20.
 */

public class WeekWeatherServiceHolder {

    private static WeekWeatherService INSTANCE;

    public static WeekWeatherService get() {
        if (INSTANCE == null) {
            INSTANCE = createInstance();
        }
        return INSTANCE;
    }

    private static WeekWeatherService createInstance() {
        return retrofit().create(WeekWeatherService.class);
    }

    private static Retrofit retrofit() {
        final String ENDPOINT = "https://api.rss2json.com/";
        return new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
