package com.example.masato.weatherforecast;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.masato.weatherforecast.api.WeatherService;
import com.example.masato.weatherforecast.api.WeatherServiceHolder;
import com.example.masato.weatherforecast.databinding.FragmentDaysForecastBinding;
import com.example.masato.weatherforecast.model.Forecasts;
import com.example.masato.weatherforecast.model.WeatherEntity;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DaysForecastFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DaysForecastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DaysForecastFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;
    private int page;

    private OnFragmentInteractionListener mListener;
    private FragmentDaysForecastBinding binding;
    public DaysForecastFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param page Parameter 1.
     * @return A new instance of fragment DaysForecastFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DaysForecastFragment newInstance(int page) {
        DaysForecastFragment fragment = new DaysForecastFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page = getArguments().getInt("page");
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
        binding.title1.setText("今日");
        binding.text1.setText("はれ");

        binding.title2.setText("明日");
        binding.text2.setText("はれ");

        binding.title3.setText("明後日");
        binding.text3.setText("はれ");


        WeatherServiceHolder.get()
                .getWeather("140010")
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

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void setView(WeatherEntity weatherEntity) {
        Forecasts oneForecasts = weatherEntity.getForecasts().get(0);
        Forecasts twoForecasts = weatherEntity.getForecasts().get(1);
        Forecasts threeForecasts = weatherEntity.getForecasts().get(2);

        binding.title1.setText(oneForecasts.getDataLabel());
        binding.text1.setText(oneForecasts.getTelop());
        Glide.with(this.getContext()).load(oneForecasts.getImage().getUrl()).into(binding.imageIcon1);

        binding.title2.setText(twoForecasts.getDataLabel());
        binding.text2.setText(twoForecasts.getTelop());
        Glide.with(this.getContext()).load(twoForecasts.getImage().getUrl()).into(binding.imageIcon2);

        binding.title3.setText(threeForecasts.getDataLabel());
        binding.text3.setText(threeForecasts.getTelop());
        Glide.with(this.getContext()).load(threeForecasts.getImage().getUrl()).into(binding.imageIcon3);

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
