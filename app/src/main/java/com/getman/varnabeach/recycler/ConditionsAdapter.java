package com.getman.varnabeach.recycler;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConditionsAdapter extends RecyclerView.Adapter<ConditionsViewHolder> {
    private String[] conditionKeys;
    private Map<String, String> conditions;

    public ConditionsAdapter(Map<String, String> conditions) {
        this.conditions = conditions;
        this.conditions.keySet().toArray(conditionKeys);
    }

    @NonNull
    @Override
    public ConditionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ConditionsViewHolder.createFrom(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ConditionsViewHolder holder, int position) {
        // todo bind both key and value
        holder.bind(conditions.get(conditionKeys[position]));
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
