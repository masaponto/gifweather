package com.example.masato.weatherforecast.api;

import com.example.masato.weatherforecast.model.giphy.GiphyEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by masato on 17/03/25.
 */

public interface GiphyService {

    @GET("/v1/gifs/random")
    public Observable<GiphyEntity> getGif(@Query("api_key") String apiKey,
                                          @Query("tag") String tag);
}
