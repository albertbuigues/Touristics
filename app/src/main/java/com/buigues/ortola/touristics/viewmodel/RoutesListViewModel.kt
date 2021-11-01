package com.buigues.ortola.touristics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buigues.ortola.touristics.model.entity.Route
import com.buigues.ortola.touristics.model.repository.FirebaseRepository
import com.buigues.ortola.touristics.model.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutesListViewModel @Inject constructor(
    roomRepository: RoomRepository,
    firebaseRepository: FirebaseRepository,
) : ViewModel()
{
    var routes: List<Route> = emptyList()

    init {
        firebaseRepository.dumpDataFromFirebase()
        viewModelScope.launch(Dispatchers.IO) {
            routes = getAllRoutes(roomRepository)
        }
    }

    private suspend fun getAllRoutes(repository: RoomRepository): List<Route>{
        return repository.getAllRoutes()
    }
}