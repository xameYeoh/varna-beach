package com.getman.varnabeach.recycler;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapAdapter extends RecyclerView.Adapter<ConditionsViewHolder> {
    private final String[] conditionKeys;
    private final Map<String, String> conditions;

    public MapAdapter(Map<String, String> conditions) {
        this.conditions = conditions;
        conditionKeys = this.conditions.keySet().toArray(new String[0]);
    }

    @NonNull
    @Override
    public ConditionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ConditionsViewHolder.createFrom(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ConditionsViewHolder holder, int position) {
        String key = conditionKeys[position];
        String value = conditions.get(key);
        holder.bind(key, value);
    }

    @Override
    public int getItemCount() {
        return conditionKeys.length;
    }
}
