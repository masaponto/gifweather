package com.example.masato.weatherforecast;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.masato.weatherforecast.api.WeekWeatherServiceHolder;
import com.example.masato.weatherforecast.databinding.FragmentWeekForecastBinding;
import com.example.masato.weatherforecast.model.weekweather.Item;
import com.example.masato.weatherforecast.model.weekweather.WeekWeatherEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WeekForecastFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WeekForecastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeekForecastFragment extends Fragment {

    // TODO: Rename and change types of parameters
    private static final String PLACE_CODE = "place_code";
    private String placeCode;

    private OnFragmentInteractionListener mListener;
    private FragmentWeekForecastBinding binding;

    public WeekForecastFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param placeCode Parameter 1.
     * @return A new instance of fragment WeekForecastFragment.
     */

    public static WeekForecastFragment newInstance(String placeCode) {
        WeekForecastFragment fragment = new WeekForecastFragment();
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
        return inflater.inflate(R.layout.fragment_week_forecast, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentWeekForecastBinding.bind(view);
        callApi(placeCode);
    }

    public void callApi(String placeCode) {
        String url = "http://weather.livedoor.com/forecast/rss/area/" + placeCode + ".xml";
        WeekWeatherServiceHolder.get()
                .getWeekWeather(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeekWeatherEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeekWeatherEntity weekWeatherEntity) {
                        if (weekWeatherEntity.getStatus().equals("error")) {
                            Log.d("error", weekWeatherEntity.getStatus());
                        } else {
                            setView(weekWeatherEntity);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Er", e.toString());
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Ooops, retry", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void setView(WeekWeatherEntity weekWeatherEntity) {
        List<Item> weekItems = weekWeatherEntity.getItems();
        List<String> icon_nums = Arrays.asList(getResources().getStringArray(R.array.icon_url));
        String telop;
        int ind;

        if (weekItems.size() != 9) {
            Toast.makeText(getContext(), "Ooops, retry", Toast.LENGTH_LONG).show();
        } else {
            // todo: try to change
            binding.title1.setText(weekItems.get(1).getDateData());
            telop = weekItems.get(1).getTelop();
            binding.text1.setText(telop);
            ind = icon_nums.indexOf(telop) + 1;
            Glide.with(this.getContext()).load(getIconUrl(ind)).into(binding.imageIcon1);

            binding.title2.setText(weekItems.get(2).getDateData());
            telop = weekItems.get(2).getTelop();
            binding.text2.setText(telop);
            ind = icon_nums.indexOf(telop) + 1;
            Glide.with(this.getContext()).load(getIconUrl(ind)).into(binding.imageIcon2);

            binding.title3.setText(weekItems.get(3).getDateData());
            telop = weekItems.get(3).getTelop();
            binding.text3.setText(telop);
            ind = icon_nums.indexOf(telop) + 1;
            Glide.with(this.getContext()).load(getIconUrl(ind)).into(binding.imageIcon3);

            binding.title4.setText(weekItems.get(4).getDateData());
            telop = weekItems.get(4).getTelop();
            binding.text4.setText(telop);
            ind = icon_nums.indexOf(telop) + 1;
            Glide.with(this.getContext()).load(getIconUrl(ind)).into(binding.imageIcon4);

            binding.title5.setText(weekItems.get(5).getDateData());
            telop = weekItems.get(5).getTelop();
            binding.text5.setText(telop);
            ind = icon_nums.indexOf(telop) + 1;
            Glide.with(this.getContext()).load(getIconUrl(ind)).into(binding.imageIcon5);

            binding.title6.setText(weekItems.get(6).getDateData());
            telop = weekItems.get(6).getTelop();
            binding.text6.setText(telop);
            ind = icon_nums.indexOf(telop) + 1;
            Glide.with(this.getContext()).load(getIconUrl(ind)).into(binding.imageIcon6);

            binding.title7.setText(weekItems.get(7).getDateData());
            telop = weekItems.get(7).getTelop();
            binding.text7.setText(telop);
            ind = icon_nums.indexOf(telop) + 1;
            Glide.with(this.getContext()).load(getIconUrl(ind)).into(binding.imageIcon7);

            binding.title8.setText(weekItems.get(8).getDateData());
            telop = weekItems.get(8).getTelop();
            binding.text8.setText(telop);
            ind = icon_nums.indexOf(telop) + 1;
            Glide.with(this.getContext()).load(getIconUrl(ind)).into(binding.imageIcon8);

        }
    }

    private String getIconUrl(int ind) {
        return "http://weather.livedoor.com/img/icon/" + ind + ".gif";
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
