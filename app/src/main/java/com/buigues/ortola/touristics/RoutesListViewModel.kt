package com.buigues.ortola.touristics

import androidx.lifecycle.ViewModel
import com.buigues.ortola.touristics.model.entity.Route
import com.buigues.ortola.touristics.repository.FirebaseRepository
import com.buigues.ortola.touristics.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoutesListViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
    private val firebaseRepository: FirebaseRepository
    ): ViewModel()
{
    init {
        firebaseRepository.dumpDataFromFirebase()
    }

    suspend fun getRoutes(): List<Route>{
        return roomRepository.getAllRoutes()
    }
}