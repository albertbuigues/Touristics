package com.buigues.ortola.touristics

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buigues.ortola.touristics.model.entity.Route

class RoutesListAdapter(private val routesList: List<Route>): RecyclerView.Adapter<RoutesListAdapter.RoutesViewHolder>()
{

    class RoutesViewHolder(val item: View): RecyclerView.ViewHolder(item) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutesViewHolder {
        return RoutesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.route_item, parent, false))
    }

    override fun onBindViewHolder(holder: RoutesViewHolder, position: Int) {
        val currentItem = routesList[position]
    }

    override fun getItemCount(): Int {
        return routesList.size
    }

}