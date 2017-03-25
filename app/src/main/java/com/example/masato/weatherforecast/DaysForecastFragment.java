package com.example.masato.weatherforecast;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.masato.weatherforecast.api.FlickrService;
import com.example.masato.weatherforecast.api.FlickrServiceHolder;
import com.example.masato.weatherforecast.api.WeatherService;
import com.example.masato.weatherforecast.api.WeatherServiceHolder;
import com.example.masato.weatherforecast.databinding.FragmentDaysForecastBinding;
import com.example.masato.weatherforecast.model.flickr.FlickrPhotoEntity;
import com.example.masato.weatherforecast.model.flickr.Photo;
import com.example.masato.weatherforecast.model.weather.Forecasts;
import com.example.masato.weatherforecast.model.weather.WeatherEntity;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import kotlin.jvm.functions.Function14;


public class DaysForecastFragment extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener {

    private static final String PLACE_CODE = "place_code";
    private static final String API_KEY = "67273c34399ef16bd17411d01bca05cf";
    private String placeCode;
    private SharedPreferences sharedPreferences;
    private SwipeRefreshLayout swipeRefreshLayout;
    private OnFragmentInteractionListener mListener;
    private FragmentDaysForecastBinding binding;
    public DaysForecastFragment() {
        // Required empty public constructor
    }

    public static DaysForecastFragment newInstance(String placeCode) {
        DaysForecastFragment fragment = new DaysForecastFragment();
        Bundle args = new Bundle();
        args.putString(PLACE_CODE, placeCode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            placeCode = getArguments().getString(PLACE_CODE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_days_forecast, container, false);
    }

    public ImageView getWeatherImageView(int n) {
        return n == 0 ? binding.imageIcon1 : binding.imageIcon2;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentDaysForecastBinding.bind(view);
        swipeRefreshLayout = binding.refresh;
        swipeRefreshLayout.setOnRefreshListener(this);

        //setWeather(placeCode);
    }

    public void setWeather(String code) {
        setForecastText(code);
        setFlickrPhoto(code, 0);
        setFlickrPhoto(code, 1);
    }

    public void setForecastText(String placeCode) {
        callWeatherApi(placeCode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeatherEntity weatherEntity) {
                        Forecasts oneForecasts = weatherEntity.getForecasts().get(0);
                        binding.forecastText1.setText(oneForecasts.getDate() + " : " + oneForecasts.getTelop());

                        Forecasts twoForecasts = weatherEntity.getForecasts().get(1);
                        binding.forecastText2.setText(twoForecasts.getDate() + " : " + twoForecasts.getTelop());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void setFlickrPhoto(String placeCode, final int n) {
        callWeatherApi(placeCode)
                .flatMap(new Function<WeatherEntity, Observable<FlickrPhotoEntity>>() {
                    @Override
                    public Observable<FlickrPhotoEntity> apply(@NonNull WeatherEntity weatherEntity) throws Exception {
                        int page = 1;
                        Forecasts Forecasts = weatherEntity.getForecasts().get(n);

                        return callFlickrPhotoApi(API_KEY, toEnglish(Forecasts.getTelop()), page);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FlickrPhotoEntity>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FlickrPhotoEntity flickrPhotoEntity) {
                        Random r = new Random();
                        int range = flickrPhotoEntity.getPhotos().getPhoto().size();
                        int ind = r.nextInt(range);
                        Photo photo = flickrPhotoEntity.getPhotos().getPhoto().get(ind);
                        Glide.with(getContext()).load(getPhotoUrl(photo)).into(getWeatherImageView(n));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private String toEnglish(String str) {

        if (str.contains("雪")) {
            return "snow";
        } else if(str.contains("雨")) {
            return "rain";
        }

        String jpStr = str.substring(0,1);
        if(jpStr.equals("晴")) {
            return "sunny day";
        } else if (jpStr.equals("雲")) {
            return "cloud";
        } else {
            return jpStr;
        }
    }

    private String getPhotoUrl(Photo photo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://farm");
        stringBuilder.append(photo.getFarm());
        stringBuilder.append(".staticflickr.com/");
        stringBuilder.append(photo.getServer());
        stringBuilder.append("/");
        stringBuilder.append(photo.getId());
        stringBuilder.append("_");
        stringBuilder.append(photo.getSecret());
        stringBuilder.append(".jpg");
        return stringBuilder.toString();
    }

    private Observable<WeatherEntity> callWeatherApi(String placeCode) {
        return WeatherServiceHolder.get()
                .getWeather(placeCode)
                .cache();
    }

    private Observable<FlickrPhotoEntity> callFlickrPhotoApi(String apiKey, String text, int page) {
        return FlickrServiceHolder.get()
                .getFlickrPhoto(apiKey, text, page);
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 更新が終了したらインジケータ非表示

                sharedPreferences =
                        getActivity().getSharedPreferences ("select_city", getContext().MODE_PRIVATE);
                String code = sharedPreferences.getString("id", "130010");
                setWeather(code);
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences =
                getActivity().getSharedPreferences ("select_city", getContext().MODE_PRIVATE);
        String code = sharedPreferences.getString("id", "130010");
        setWeather(code);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
