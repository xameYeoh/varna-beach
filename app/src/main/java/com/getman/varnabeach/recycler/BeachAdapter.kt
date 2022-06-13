package com.getman.varnabeach.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.getman.varnabeach.recycler.BeachViewHolder.Companion.create
import com.getman.varnabeach.room.Beach

class BeachAdapter : ListAdapter<Beach?, BeachViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Beach?> =
            object : DiffUtil.ItemCallback<Beach?>() {
                override fun areItemsTheSame(oldItem: Beach, newItem: Beach): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Beach, newItem: Beach): Boolean {
                    return oldItem == newItem
                }
            }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeachViewHolder {
        return create(parent)
    }

    override fun onBindViewHolder(holder: BeachViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }
}