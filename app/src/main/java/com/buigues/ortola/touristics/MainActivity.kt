package com.buigues.ortola.touristics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.buigues.ortola.touristics.databinding.ActivityMainBinding
import com.buigues.ortola.touristics.repository.FirebaseRepository
import com.buigues.ortola.touristics.viewmodel.RoutesListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val firebaseRepository by lazy { FirebaseRepository(application) }
    private val routesListViewModel by lazy { RoutesListViewModel(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            firebaseRepository.dumpDataFromFirebase()
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        var adapter = RoutesListAdapter(emptyList())
        val recyclerView = binding.recyclerRoutes
        CoroutineScope(Dispatchers.IO).launch { adapter = RoutesListAdapter(routesListViewModel.getAllRoutes()) }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}