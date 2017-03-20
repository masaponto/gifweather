package com.example.masato.weatherforecast.api;

import com.example.masato.weatherforecast.model.weekweather.WeekWeatherEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by masato on 17/03/20.
 */

public interface WeekWeatherService {

    @GET("/v1/api.json")
    public Observable<WeekWeatherEntity> getWeekWeather(@Query("rss_url") final String url);
}
