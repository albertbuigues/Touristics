package com.buigues.ortola.touristics.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.buigues.ortola.touristics.databinding.ActivityMainBinding
import com.buigues.ortola.touristics.model.dao.RouteDao
import com.buigues.ortola.touristics.model.entity.Route
import com.buigues.ortola.touristics.model.repository.RoomRepository
import com.buigues.ortola.touristics.viewmodel.RoutesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val routesListViewModel: RoutesListViewModel by viewModels()
    private lateinit var myAdapter: RoutesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()
    }

    private fun initRecycler() {
        val recyclerView = binding.recyclerRoutes
        myAdapter = RoutesListAdapter(routesListViewModel.routes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = myAdapter
    }
}