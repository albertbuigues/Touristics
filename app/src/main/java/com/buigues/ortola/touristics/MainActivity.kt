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
    private lateinit var firebaseRepository: FirebaseRepository
    private lateinit var routesListViewModel: RoutesListViewModel
    private val app = TouristicsApp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            firebaseRepository = FirebaseRepository(app)
            firebaseRepository.dumpDataFromFirebase()
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerView = binding.recyclerRoutes
        routesListViewModel = RoutesListViewModel(app)
        val adapter = RoutesListAdapter(routesListViewModel.getAllRoutes())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}