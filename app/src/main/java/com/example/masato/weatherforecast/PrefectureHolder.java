package com.example.masato.weatherforecast;

import android.content.Context;
import android.util.Log;

import com.example.masato.weatherforecast.model.Prefecture.Prefecture;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by masato on 17/03/22.
 */

public class PrefectureHolder {

    private static ArrayList<Prefecture> INSTANCE;

    public static ArrayList<Prefecture> get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = createInstance(context);
        }
        return INSTANCE;
    }

    private static ArrayList<Prefecture> createInstance(Context context) {
        Log.d("prefectureHolder", "called");
        Gson gson = new Gson();
        JsonReader reader;
        InputStream inputStream = context.getResources().openRawResource(R.raw.prefecture);
        reader = new JsonReader(new InputStreamReader(inputStream));
        return gson.fromJson(reader, new TypeToken<ArrayList<Prefecture>>(){}.getType());
    }


}
