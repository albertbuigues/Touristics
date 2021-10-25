package com.buigues.ortola.touristics.viewmodel

import androidx.lifecycle.ViewModel
import com.buigues.ortola.touristics.model.dao.RouteDao
import com.buigues.ortola.touristics.model.entity.Route
import com.buigues.ortola.touristics.model.repository.FirebaseRepository
import com.buigues.ortola.touristics.model.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutesListViewModel @Inject constructor(
    roomRepository: RoomRepository,
    firebaseRepository: FirebaseRepository,
) : ViewModel()
{
    var dbRoutes: List<Route> = roomRepository.getAllRoutes()

    init {
        firebaseRepository.dumpDataFromFirebase()
    }
}