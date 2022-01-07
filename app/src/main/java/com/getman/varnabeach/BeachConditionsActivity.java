package com.getman.varnabeach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.getman.varnabeach.databinding.ActivityBeachDetailBinding;
import com.getman.varnabeach.lifecycle.BeachConditionsViewModel;
import com.getman.varnabeach.room.Beach;

import java.util.Map;

public class BeachConditionsActivity extends AppCompatActivity {
    public static final String BEACH = "beach";
    private ActivityBeachDetailBinding binding;
    private Beach beach;
    private BeachConditionsViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBeachDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getBeachFromIntent();

        bindViewsToBeachInfo();

        setBeachConditionsViewModel();

        setObserverForViewModel();
    }

    private void setObserverForViewModel() {
        model.getConditions(beach.lat, beach.lng, this::displayLoadingInfo)
                .observe(this, this::displayConditions);
    }

    private void setBeachConditionsViewModel() {
        model = new ViewModelProvider(this)
                .get(BeachConditionsViewModel.class);
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

    private void displayLoadingInfo() {
        binding.testView.setText("Loading...");
    }

    private void displayConditions(Map<String, String> conditions) {
            StringBuilder builder = new StringBuilder();
            for (String key : conditions.keySet()) {
                builder.append(key)
                        .append(": ")
                        .append(conditions.get(key))
                        .append("\n");
            }
            TextView test = findViewById(R.id.test_view);
            test.setText(builder.toString());
    }
}