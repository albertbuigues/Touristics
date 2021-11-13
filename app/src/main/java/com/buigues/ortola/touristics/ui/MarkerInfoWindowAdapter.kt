package com.buigues.ortola.touristics.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.buigues.ortola.touristics.R
import com.buigues.ortola.touristics.model.entity.PointOfInterest
import com.bumptech.glide.Glide
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.bumptech.glide.request.RequestListener
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


class MarkerInfoWindowAdapter (private val context: Context): GoogleMap.InfoWindowAdapter
{

    override fun getInfoContents(marker: Marker): View? {
        // 1rst - Get tag
        val pointOfInterest = marker.tag as? PointOfInterest ?: return null

        // 2nd - Inflate view and set values
        val view = LayoutInflater.from(context).inflate(R.layout.marker_content, null)

        view.findViewById<TextView>(R.id.marker_title).text = pointOfInterest.title

        val image = view.findViewById<ImageView>(R.id.marker_image)
        Glide.with(context).load(pointOfInterest.imageUrl).into(image)

        view.findViewById<ImageView>(R.id.marker_play_btn).setOnClickListener {
            TODO("Play the audio")
        }

        view.findViewById<ImageView>(R.id.marker_pause_btn).setOnClickListener {
            TODO("Pause the audio")
        }

        view.findViewById<ImageView>(R.id.marker_stop_btn).setOnClickListener {
            TODO("Stop the audio")
        }

        return view
    }

    override fun getInfoWindow(marker: Marker): View? {
        // This indicates that the default window (white bubble) should be used
        return null
    }

}