package com.buigues.ortola.touristics.viewmodel

import androidx.lifecycle.ViewModel
import com.buigues.ortola.touristics.model.entity.PointOfInterest
import com.buigues.ortola.touristics.model.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(private val repository: RoomRepository): ViewModel() {

    suspend fun getRoutePoints(routeId: Int): List<PointOfInterest> {
        return repository.getPointsByRouteId(routeId)
    }
}