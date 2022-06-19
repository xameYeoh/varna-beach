package com.getman.varnabeach

import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.getman.varnabeach.recycler.BeachAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.getman.varnabeach.lifecycle.BeachListViewModel
import com.getman.varnabeach.databinding.ActivityMainBinding
import com.getman.varnabeach.room.Beach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)
    }
}