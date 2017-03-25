package com.example.masato.weatherforecast.api;

import com.example.masato.weatherforecast.model.flickr.FlickrPhotoEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by masato on 17/03/25.
 */

public interface FlickrService {

    @GET("/services/rest/?method=flickr.photos.search&format=json&nojsoncallback=1&safe_search=1&tags=sky")
    public Observable<FlickrPhotoEntity> getFlickrPhoto(@Query("api_key") String apiKey,
                                                        @Query("text") String text,
                                                        @Query("page") int page);
}
