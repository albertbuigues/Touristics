package com.buigues.ortola.touristics.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.buigues.ortola.touristics.R
import com.buigues.ortola.touristics.databinding.RouteItemBinding
import com.buigues.ortola.touristics.model.entity.Route
import com.bumptech.glide.Glide

class RoutesListAdapter(): RecyclerView.Adapter<RoutesListAdapter.RoutesViewHolder>()
{
    private var routesList = emptyList<Route>()

    class RoutesViewHolder(private val binding: RouteItemBinding): RecyclerView.ViewHolder(binding.root) {

        val arrowIcon: ImageView = binding.expandIconImage
        val startRouteButton = binding.startRouteBtn
        private val detailsPortion: ConstraintLayout = binding.expandiblePortionLayout
        var isExpanded: Boolean = false

        fun bind(route: Route) {
            binding.routeTitleTv.text = route.title
            binding.routePeriodTv.text = route.historicPeriod
            binding.routeDescriptionTv.text = route.description
            Glide.with(binding.root.context).load(route.imageUrl).into(binding.routeImageView)
        }

        fun expandOrHideRouteDetails() {
            if (!isExpanded) {
                detailsPortion.visibility = View.VISIBLE
                isExpanded = true
                arrowIcon.setImageResource(R.drawable.ic_expand_less)
            } else {
                detailsPortion.visibility = View.GONE
                isExpanded = false
                arrowIcon.setImageResource(R.drawable.ic__expand_more)
            }
        }

        fun navigateToMap(routeId: Int) {
            val mapsIntent = Intent(binding.root.context, MapsActivity::class.java)
            mapsIntent.putExtra("routeId", routeId)
            ContextCompat.startActivity(binding.root.context, mapsIntent, Bundle())
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutesViewHolder {
        val view = RouteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoutesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoutesViewHolder, position: Int) {
        holder.bind(routesList[position])
        holder.arrowIcon.setOnClickListener {
            holder.expandOrHideRouteDetails()
        }
        holder.startRouteButton.setOnClickListener {
            holder.navigateToMap(routesList[position].id)
        }
    }

    override fun getItemCount(): Int {
        return routesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(routes:List<Route>) {
        this.routesList = routes
        notifyDataSetChanged()
    }

}