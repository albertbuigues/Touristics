package com.buigues.ortola.touristics.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.buigues.ortola.touristics.R
import com.buigues.ortola.touristics.model.entity.PointOfInterest
import com.bumptech.glide.Glide
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker


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

        return view
    }

    override fun getInfoWindow(marker: Marker): View? {
        // This indicates that the default window (white bubble) should be used
        return null
    }

}