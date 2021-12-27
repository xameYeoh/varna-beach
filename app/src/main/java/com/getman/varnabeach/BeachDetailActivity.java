package com.getman.varnabeach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.getman.varnabeach.room.Beach;

public class BeachDetailActivity extends AppCompatActivity {
    public static final String BEACH = "beach";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beach_detail);

        Intent intent = getIntent();
        Beach beach = (Beach) intent.getSerializableExtra(BEACH);

        TextView test = findViewById(R.id.test_view);
        String text = beach.name;
        test.setText(text);
    }
}