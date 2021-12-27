package com.getman.varnabeach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.getman.varnabeach.lifecycle.BeachViewModel;
import com.getman.varnabeach.recycler.BeachAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BeachAdapter adapter = new BeachAdapter();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        BeachViewModel vm = new ViewModelProvider(this).get(BeachViewModel.class);
        vm.getAllBeaches().observe(this, adapter::submitList);
    }
}