package com.buigues.ortola.touristics.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.buigues.ortola.touristics.model.entity.Route
import com.buigues.ortola.touristics.model.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoutesListViewModel @Inject constructor(
    roomRepository: RoomRepository,
) : ViewModel()
{
    val routes: LiveData<List<Route>> = roomRepository.listOfRoutes
}