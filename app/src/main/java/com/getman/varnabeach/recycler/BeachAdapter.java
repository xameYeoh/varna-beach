package com.getman.varnabeach.recycler;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.getman.varnabeach.room.Beach;

public class BeachAdapter extends ListAdapter<Beach, BeachViewHolder> {

    public BeachAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public BeachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return BeachViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull BeachViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private static final DiffUtil.ItemCallback<Beach> DIFF_CALLBACK = new DiffUtil.ItemCallback<Beach>() {
        @Override
        public boolean areItemsTheSame(@NonNull Beach oldItem, @NonNull Beach newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Beach oldItem, @NonNull Beach newItem) {
            return oldItem.equals(newItem);
        }
    };
}
