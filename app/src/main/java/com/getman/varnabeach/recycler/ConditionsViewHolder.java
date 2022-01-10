package com.getman.varnabeach.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.getman.varnabeach.R;
import com.getman.varnabeach.databinding.CardTwoTextsBinding;

public class ConditionsViewHolder extends RecyclerView.ViewHolder {
    private final CardTwoTextsBinding binding;

    public ConditionsViewHolder(View view) {
        super(view);

        binding = CardTwoTextsBinding.bind(view);
    }

    public static ConditionsViewHolder createFrom(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_two_texts, parent, false );

        return new ConditionsViewHolder(view);
    }

    public void bind(CharSequence key, CharSequence value) {
        binding.textKey.setText(key);
        binding.textValue.setText(value);
    }
}
