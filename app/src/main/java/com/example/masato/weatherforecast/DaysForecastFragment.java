package com.example.masato.weatherforecast;

import android.content.Context;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.masato.weatherforecast.api.WeatherServiceHolder;
import com.example.masato.weatherforecast.databinding.FragmentDaysForecastBinding;
import com.example.masato.weatherforecast.model.weather.Forecasts;
import com.example.masato.weatherforecast.model.weather.WeatherEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class DaysForecastFragment extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener {

    private static final String PLACE_CODE = "place_code";

    private String placeCode;

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentDaysForecastBinding.bind(view);
        swipeRefreshLayout = binding.refresh;
        swipeRefreshLayout.setOnRefreshListener(this);
        callApi();
    }

    public void callApi() {
        WeatherServiceHolder.get()
                .getWeather(placeCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeatherEntity weatherEntity) {
                        setView(weatherEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), "Ooops, retry", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void setView(WeatherEntity weatherEntity) {

        if (weatherEntity.getForecasts().size() >= 2) {
            Forecasts oneForecasts = weatherEntity.getForecasts().get(0);
            Forecasts twoForecasts = weatherEntity.getForecasts().get(1);
            String description = weatherEntity.getDescription().getText().replaceAll("\n", "");

            binding.title1.setText(oneForecasts.getDateData());
            binding.text1.setText(oneForecasts.getTelop());
            Glide.with(this.getContext()).load(oneForecasts.getImage().getUrl()).into(binding.imageIcon1);

            binding.title2.setText(twoForecasts.getDateData());
            binding.text2.setText(twoForecasts.getTelop());
            Glide.with(this.getContext()).load(twoForecasts.getImage().getUrl()).into(binding.imageIcon2);

            binding.description.setText(description);

        } else {
            Toast.makeText(getContext(), "Ooops, retry", Toast.LENGTH_LONG).show();
        }

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
                callApi();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
