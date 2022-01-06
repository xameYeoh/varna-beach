package com.getman.varnabeach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.getman.varnabeach.lifecycle.BeachConditionsViewModel;
import com.getman.varnabeach.room.Beach;

public class BeachDetailActivity extends AppCompatActivity {
    public static final String BEACH = "beach";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beach_detail);

        Intent intent = getIntent();
        Beach beach = (Beach) intent.getSerializableExtra(BEACH);

        BeachConditionsViewModel model = new ViewModelProvider(this)
                .get(BeachConditionsViewModel.class);
        model.requestConditions(beach.lat, beach.lng);
        model.getConditions().observe(this, (conditions) -> {
            StringBuilder builder = new StringBuilder();
            for (String key : conditions.keySet()) {
                builder.append(key)
                        .append(": ")
                        .append(conditions.get(key))
                        .append("\n");
            }
            TextView test = findViewById(R.id.test_view);
            test.setText(builder.toString());
        });
    }
}