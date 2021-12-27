package com.getman.varnabeach.data;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.getman.varnabeach.BuildConfig;
import com.getman.varnabeach.room.Beach;
import com.getman.varnabeach.room.BeachDAO;
import com.getman.varnabeach.room.BeachDatabase;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    private static final String URL_GET = "https://api.stormglass.io/v2/weather/point";
    private static final String PARAMS = "windSpeed,waveHeight,airTemperature,waterTemperature";
    private static final String KEY = BuildConfig.STORMGLASS_KEY;

    private final Context context;
    private final LiveData<List<Beach>> allBeaches;
    private final BeachDAO beachDAO;

    public Repository(Application application) {
        context = application;
        BeachDatabase db = Room.databaseBuilder(context, BeachDatabase.class, "beach_database").build();

        beachDAO = db.beachDao();
        allBeaches = beachDAO.getAll();
    }

    public LiveData<List<Beach>> getAllBeaches() {
        return allBeaches;
    }

    public void requestAndDisplayConditions(String lat, String lng) {
        VolleyHelper helper = VolleyHelper.getInstance(context);
        String lastHour = Instant.now().truncatedTo(ChronoUnit.HOURS).toString();

        String url = URL_GET +
                "?lat=" + lat +
                "&lng=" + lng +
                "&params=" + PARAMS +
                "&start=" + lastHour;

        CacheRequest request = new CacheRequest(
                Request.Method.GET,
                url,
                this::displayOnScreen,
                this::displayError) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("Authorization", KEY);
                return params;
            }
        };

        helper.addToRequestQueue(request);
    }

    private void displayOnScreen(NetworkResponse response) {

    }

    private void displayError(VolleyError error) {

    }
}
