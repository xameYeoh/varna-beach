package com.getman.varnabeach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.getman.varnabeach.databinding.ActivityMainBinding;
import com.getman.varnabeach.lifecycle.BeachListViewModel;
import com.getman.varnabeach.recycler.BeachAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        BeachAdapter adapter = new BeachAdapter();
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        BeachListViewModel vm = new ViewModelProvider(this).get(BeachListViewModel.class);
        vm.getAllBeaches().observe(this, adapter::submitList);
    }
}