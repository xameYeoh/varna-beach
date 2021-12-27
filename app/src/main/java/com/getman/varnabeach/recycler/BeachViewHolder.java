package com.getman.varnabeach.recycler;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.getman.varnabeach.R;
import com.getman.varnabeach.room.Beach;

public class BeachViewHolder extends RecyclerView.ViewHolder {

    private final ImageView image;
    private final TextView title;
    private final TextView description;

    private BeachViewHolder(View view) {
        super(view);

        image = view.findViewById(R.id.card_image);
        title = view.findViewById(R.id.card_title);
        description = view.findViewById(R.id.card_description);
    }

    public static BeachViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.beach_card, parent, false);
        return new BeachViewHolder(view);
    }

    public void bind(Beach beach) {
        image.setImageURI(Uri.parse(beach.imageURI));
        title.setText(beach.name);
        description.setText(beach.description);
    }
}
