package com.example.masato.weatherforecast;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.masato.weatherforecast.model.Prefecture.Prefecture;

import java.util.ArrayList;

/**
 * Created by masato on 17/03/22.
 */

public class PrefectureArrayAdapter extends ArrayAdapter<Prefecture> {

    private int resource;
    private ArrayList<Prefecture> prefectures;
    private LayoutInflater inflater;

    public PrefectureArrayAdapter(Context context, int resource, ArrayList<Prefecture> prefectures) {
        super(context, resource, prefectures);
        this.resource = resource;
        this.prefectures = prefectures;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView != null) {
            view = convertView;
        }
        else {
            view = inflater.inflate(resource, null);
        }

        // リストビューに表示する要素を取得
        Prefecture pref = prefectures.get(position);

        // タイトルを設定
        TextView text = (TextView) view.findViewById(R.id.title);
        text.setText(pref.getPref());

        return view;
    }

}

