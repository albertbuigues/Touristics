package com.buigues.ortola.touristics.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.buigues.ortola.touristics.R
import com.buigues.ortola.touristics.model.entity.PointOfInterest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker


class MarkerInfoWindowAdapter (private val context: Context): GoogleMap.InfoWindowAdapter
{

    private val window: View = LayoutInflater.from(context).inflate(R.layout.marker_content, null)

    override fun getInfoContents(marker: Marker): View? {
        // 1rst - Get tag
        val pointOfInterest = marker.tag as? PointOfInterest ?: return null

        // 2nd - Inflate view and set values

        window.findViewById<TextView>(R.id.marker_title).text = pointOfInterest.title
        window.findViewById<TextView>(R.id.marker_description).text = pointOfInterest.description
        window.findViewById<TextView>(R.id.marker_description).movementMethod = ScrollingMovementMethod()


        val image = window.findViewById<ImageView>(R.id.marker_image)
        Glide
            .with(context)
            .load(pointOfInterest.imageUrl)
            .addListener(object : RequestListener<Drawable>{
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    Log.d("glide","Error loading the image url")
                    return false
                }
                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    Log.d("glide","Loaded")
                    Handler(Looper.myLooper()!!).postDelayed({
                        if (marker.isInfoWindowShown) {
                            marker.showInfoWindow()
                        }
                    }, 100)

                    return false
                }

            })
            .into(image)

        return window
    }

    override fun getInfoWindow(marker: Marker): View? {
        // This indicates that the default window (white bubble) should be used
        return null
    }

}