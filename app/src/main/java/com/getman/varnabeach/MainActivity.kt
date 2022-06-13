package com.getman.varnabeach

import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.getman.varnabeach.recycler.BeachAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.getman.varnabeach.lifecycle.BeachListViewModel
import com.getman.varnabeach.databinding.ActivityMainBinding
import com.getman.varnabeach.room.Beach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var beachListViewModel: BeachListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)
        val adapter = BeachAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        beachListViewModel.allBeaches.observe(this) { list: List<Beach?> -> adapter.submitList(list) }
    }
}