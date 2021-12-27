package com.getman.varnabeach.data;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.getman.varnabeach.BuildConfig;
import com.getman.varnabeach.room.Beach;
import com.getman.varnabeach.room.BeachDAO;
import com.getman.varnabeach.room.BeachDatabase;

import org.json.JSONException;
import org.json.JSONObject;

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
    private final Map<String, String> beachConditions;
    private final BeachDAO beachDAO;

    public Repository(Application application) {
        context = application;
        BeachDatabase db = BeachDatabase.getInstance(application);

        beachDAO = db.beachDao();
        allBeaches = beachDAO.getAllOrderByName();

        beachConditions = new HashMap<>();
    }

    public LiveData<List<Beach>> getAllBeaches() {
        return allBeaches;
    }

    public Map<String, String> getBeachConditions() { return beachConditions; }

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
                this::updateMap,
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

    private void updateMap(NetworkResponse response) {
        try {
            JSONObject jsonResponse = new JSONObject(new String(response.data));
        }
        catch (JSONException je) {
            Log.d("Parse Error", je.toString());
        }
    }

    private void displayError(VolleyError error) {
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
    }
}
