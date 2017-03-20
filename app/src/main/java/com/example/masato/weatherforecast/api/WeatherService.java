package com.example.masato.weatherforecast.api;

import com.example.masato.weatherforecast.model.weather.WeatherEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by masato on 17/03/20.
 */

public interface WeatherService {

    @GET("/forecast/webservice/json/v1")
    public Observable<WeatherEntity> getWeather(@Query("city") final String city);

}
