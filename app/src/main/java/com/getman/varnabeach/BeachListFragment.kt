package com.getman.varnabeach

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.getman.varnabeach.databinding.FragmentBeachListBinding
import com.getman.varnabeach.lifecycle.BeachListViewModel
import com.getman.varnabeach.recycler.BeachAdapter
import com.getman.varnabeach.room.Beach
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeachListFragment : Fragment() {
    private val beachListViewModel: BeachListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBeachListBinding.inflate(
            layoutInflater
        )
        val adapter = BeachAdapter {
            beachListViewModel.select(it)

            val action =
                BeachListFragmentDirections.actionBeachListFragmentToBeachConditionsFragment()
            findNavController().navigate(action)
        }
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(context)

        beachListViewModel.allBeaches.observe(requireActivity()) { list: List<Beach?> ->
            adapter.submitList(
                list
            )
        }
        return binding.root
    }
}