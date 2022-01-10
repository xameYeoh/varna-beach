package com.getman.varnabeach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.getman.varnabeach.databinding.ActivityBeachDetailBinding;
import com.getman.varnabeach.lifecycle.BeachConditionsViewModel;
import com.getman.varnabeach.recycler.MapAdapter;
import com.getman.varnabeach.room.Beach;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BeachConditionsActivity extends AppCompatActivity {
    public static final String BEACH = "beach";
    private ActivityBeachDetailBinding binding;
    private Beach beach;
    private BeachConditionsViewModel model;
    private MapAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBeachDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.beachDescription.textLayout.setGravity(0);

        getBeachFromIntent();

        bindViewsToBeachInfo();

        setBeachConditionsViewModel();

        setObserverForViewModel();
    }

    private void bindViewsToBeachInfo() {
        binding.beachDescription.cardImage.setImageURI(Uri.parse(beach.imageURI));
        binding.beachDescription.cardDescription.setText(beach.description);
        binding.beachDescription.cardTitle.setText(beach.name);
    }

    private void getBeachFromIntent() {
        Intent intent = getIntent();
        beach = (Beach) intent.getSerializableExtra(BEACH);
    }

    private void setAdapterForRecycler(Map<String, String> conditions) {
        recyclerAdapter = new MapAdapter(conditions);
        binding.recyclerViewConditions.setAdapter(recyclerAdapter);
        binding.recyclerViewConditions.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setBeachConditionsViewModel() {
        model = new ViewModelProvider(this)
                .get(BeachConditionsViewModel.class);
    }

    private void setObserverForViewModel() {
        model.getConditions(beach.lat, beach.lng, this::displayLoadingInfo)
                .observe(this, this::configureAndDisplayMapAndDisableLoadingInformation);
    }

    private void displayLoadingInfo() {
        binding.testView.setText(getResources().getText(R.string.loading));
    }

    private void configureAndDisplayMapAndDisableLoadingInformation(Map<String, String> conditions) {
        conditions = makeMapReadable(conditions);

        setAdapterForRecycler(conditions);

        binding.testView.setVisibility(View.INVISIBLE);
    }

    private Map<String, String> makeMapReadable(Map<String, String> conditions) {
        UnitFormat units = new UnitFormat(getResources());

        Map<String, String> readable = new HashMap<>();

        for (String key : conditions.keySet()) {
            String value = conditions.get(key);
            value = value + " " + units.getUnitFor(value);

            key = UnitFormat.convertCamelCaseToNormal(key);

            readable.put(key, value);
        }

        return readable;
    }

    public static class UnitFormat {
        private static final Map<String, String> UNITS = new HashMap<>();

        public UnitFormat(Resources r) {
            UNITS.put("SPEED", r.getString(R.string.speed_unit));
            UNITS.put("HEIGHT", r.getString(R.string.distance_unit));
            UNITS.put("TEMPERATURE", r.getString(R.string.temperature_unit));
        }

        public String getUnitFor(String param) {
            param = param.toUpperCase(Locale.ROOT);
            for (String parameterType : UNITS.keySet()) {
                if (param.contains(parameterType)) return UNITS.get(parameterType);
            }
            return null;
        }

        public static String convertCamelCaseToNormal(String text) {
            String pascalCase = text.substring(0, 1).toUpperCase(Locale.ROOT)
                    + text.substring(1);
            String[] words = pascalCase.split("(?=[A-ZА-Я])");

            StringBuilder builder = new StringBuilder();
            for (String word : words) {
                builder.append(word).append(" ");
            }

            return builder.toString();
        }
    }
}