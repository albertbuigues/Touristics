package com.buigues.ortola.touristics.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buigues.ortola.touristics.databinding.RouteItemBinding
import com.buigues.ortola.touristics.model.entity.Route
import com.squareup.picasso.Picasso

class RoutesListAdapter(private var routesList: List<Route>): RecyclerView.Adapter<RoutesListAdapter.RoutesViewHolder>()
{

    class RoutesViewHolder(private val binding: RouteItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(route: Route) {
            binding.routeTitleTv.text = route.title
            binding.routePeriodTv.text = route.historicPeriod
            Picasso.get().load(route.imageUrl).into(binding.routeImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutesViewHolder {
        val view = RouteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoutesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoutesViewHolder, position: Int) {
        holder.bind(routesList[position])
    }

    override fun getItemCount(): Int {
        return routesList.size
    }

}