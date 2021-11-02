package com.buigues.ortola.touristics.model.repository

import androidx.lifecycle.LiveData
import com.buigues.ortola.touristics.model.dao.RouteDao
import com.buigues.ortola.touristics.model.entity.Route
import javax.inject.Inject

class RoomRepository @Inject constructor(private val routeDao: RouteDao)
{
    val listOfRoutes: LiveData<List<Route>> = routeDao.getAllRoutes()

}