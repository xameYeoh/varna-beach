package com.getman.varnabeach.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ConditionsViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;

    public ConditionsViewHolder(View view) {
        super(view);

        textView = (TextView) view;
    }

    public static ConditionsViewHolder createFrom(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false );

        return new ConditionsViewHolder(view);
    }

    public void bind(CharSequence text) {
        textView.setText(text);
    }
}
