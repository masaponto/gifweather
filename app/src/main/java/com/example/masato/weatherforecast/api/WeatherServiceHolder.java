package com.example.masato.weatherforecast.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by masato on 17/03/20.
 */

public class WeatherServiceHolder {

    private static WeatherService INSTANCE;

    public static WeatherService get() {
        if (INSTANCE == null) {
            INSTANCE = createInstance();
        }
        return INSTANCE;
    }

    private static WeatherService createInstance() {
        return retrofit().create(WeatherService.class);
    }

    private static Retrofit retrofit() {
        final String ENDPOINT = "http://weather.livedoor.com/";
        return new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }



}
