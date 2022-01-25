package com.getman.varnabeach.recycler;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.getman.varnabeach.BeachConditionsActivity;
import com.getman.varnabeach.R;
import com.getman.varnabeach.databinding.BeachCardBinding;
import com.getman.varnabeach.room.Beach;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class BeachViewHolder extends RecyclerView.ViewHolder {

    private final ImageView image;
    private final TextView title;
    private final TextView description;

    private BeachViewHolder(View view) {
        super(view);

        BeachCardBinding binding = BeachCardBinding.bind(view);

        image = binding.beachInformation.cardImage;
        title = binding.beachInformation.cardTitle;
        description = binding.beachInformation.cardDescription;
    }

    public static BeachViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.beach_card, parent, false);

        return new BeachViewHolder(view);
    }

    public void bind(Beach beach) {
        image.setImageDrawable(getBeachImageDrawable(beach));
        if (image.getDrawable() == null) Log.e("Image", "Drawable null");

        title.setText(beach.name);
        description.setText(beach.description);

        image.getRootView().setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), BeachConditionsActivity.class);
            intent.putExtra(BeachConditionsActivity.BEACH, beach);
            image.getContext().startActivity(intent);
        });
    }

    private Drawable getBeachImageDrawable(Beach beach) {
        try {
            InputStream is = image.getContext().getContentResolver()
                    .openInputStream(Uri.parse(beach.imageURI));
            return Drawable.createFromStream(is, beach.imageURI);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

