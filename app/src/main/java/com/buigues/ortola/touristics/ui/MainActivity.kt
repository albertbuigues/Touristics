package com.buigues.ortola.touristics.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.buigues.ortola.touristics.databinding.ActivityMainBinding
import com.buigues.ortola.touristics.viewmodel.RoutesListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val routesListViewModel: RoutesListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()
    }

    private fun initRecycler() {
        val recyclerView = binding.recyclerRoutes
        val routesAdapter = RoutesListAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = routesAdapter
        routesListViewModel.routes.observe(this, {route -> routesAdapter.setData(route)})
    }
}