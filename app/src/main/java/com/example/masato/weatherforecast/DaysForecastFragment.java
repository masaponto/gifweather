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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.masato.weatherforecast.api.GiphyServiceHolder;
import com.example.masato.weatherforecast.api.WeatherServiceHolder;
import com.example.masato.weatherforecast.databinding.FragmentDaysForecastBinding;
import com.example.masato.weatherforecast.model.giphy.GiphyEntity;
import com.example.masato.weatherforecast.model.weather.Forecasts;
import com.example.masato.weatherforecast.model.weather.WeatherEntity;

import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class DaysForecastFragment extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener {

    private static final String PLACE_CODE = "place_code";
    private static final String API_KEY = "dc6zaTOxFJmzC";
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
        setWeather(placeCode);
    }

    public void setWeather(String code) {
        setForecastText(code);
        setGiphy(code, 0);
        setGiphy(code, 1);
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


    public void setGiphy(String placeCode, final int n) {
        callWeatherApi(placeCode)
                .flatMap(new Function<WeatherEntity, Observable<GiphyEntity>>() {
                    @Override
                    public Observable<GiphyEntity> apply(@NonNull WeatherEntity weatherEntity) throws Exception {
                        Forecasts Forecasts = weatherEntity.getForecasts().get(n);
                        return callGiphyApi(API_KEY, toEnglish(Forecasts.getTelop()));
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GiphyEntity>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GiphyEntity giphyEntity) {
                        Random r = new Random();
                        if(giphyEntity.getPagination().getCount() > 0) {
                            int ind = r.nextInt(giphyEntity.getPagination().getCount());
                            String url = giphyEntity.getData().get(ind).getImages().getImage().getUrl();
                            Glide.with(getContext()).load(url).into(getWeatherImageView(n));
                        } else {
                            Toast.makeText(getContext(), "Ooops, Something went wrong", Toast.LENGTH_SHORT);
                        }
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
        Random rnd = new Random();
        int r = rnd.nextInt(2);

        if (str.contains("雪")) {
            if (r == 0) {
                return "glacier";
            } else {
                return "snow";
            }
        } else if(str.contains("雨")) {
            if (r == 0) {
                return "rain";
            } else {
                return "umbrella";
            }
        }


        String jpStr = str.substring(0,1);
        if(jpStr.equals("晴")) {
            if (r == 0) {
                return "sun";
            } else {
                return "sunny";
            }
        } else if (jpStr.equals("曇")) {
            if (r == 0) {
                return "cloud";
            } else {
                return "fog";
            }
        } else {
            return "Sky";
        }
    }


    private Observable<WeatherEntity> callWeatherApi(String placeCode) {
        return WeatherServiceHolder.get()
                .getWeather(placeCode)
                .cache();
    }

    private Observable<GiphyEntity> callGiphyApi(String apiKey, String text) {
        return GiphyServiceHolder.get()
                .getGif(apiKey, text);
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
        if (!placeCode.equals(code)) {
            setWeather(code);
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
