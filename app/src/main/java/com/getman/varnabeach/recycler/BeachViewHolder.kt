package com.getman.varnabeach.recycler

import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.getman.varnabeach.R
import com.getman.varnabeach.databinding.BeachCardBinding
import com.getman.varnabeach.lifecycle.BeachListViewModel
import com.getman.varnabeach.room.Beach
import java.io.FileNotFoundException
import javax.inject.Inject

class BeachViewHolder private constructor(
    view: View,
    onItemClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(view) {
    private val image: ImageView
    private val title: TextView
    private val description: TextView

    init {
        itemView.setOnClickListener {
            onItemClicked(bindingAdapterPosition)
        }

        val binding = BeachCardBinding.bind(view)
        image = binding.beachInformation.cardImage
        title = binding.beachInformation.cardTitle
        description = binding.beachInformation.cardDescription
    }

    private val contentResolver = image.context.contentResolver

    companion object {
        @JvmStatic
        fun create(
            parent: ViewGroup,
            onItemClicked: (Int) -> Unit
        ): BeachViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.beach_card, parent, false)
            return BeachViewHolder(view, onItemClicked)
        }
    }

    fun bind(beach: Beach) {
        image.setImageDrawable(getBeachImageDrawable(beach))
        if (image.drawable == null) Log.e("Image", "Drawable null")
        title.text = beach.name
        description.text = beach.description
    }

    private fun getBeachImageDrawable(beach: Beach): Drawable? {
        try {
            val inputStream = contentResolver
                .openInputStream(Uri.parse(beach.imageURI))
            return Drawable.createFromStream(inputStream, beach.imageURI)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        return null
    }
}