package com.buigues.ortola.touristics.model.repository

import androidx.lifecycle.LiveData
import com.buigues.ortola.touristics.model.dao.PointsDao
import com.buigues.ortola.touristics.model.dao.RouteDao
import com.buigues.ortola.touristics.model.entity.PointOfInterest
import com.buigues.ortola.touristics.model.entity.Route
import javax.inject.Inject

class RoomRepository @Inject constructor(routeDao: RouteDao, private val pointsDao: PointsDao)
{
    val listOfRoutes: LiveData<List<Route>> = routeDao.getAllRoutes()

    suspend fun getPointsByRouteId(routeId: Int): List<PointOfInterest> {
        return pointsDao.getRoutePoints(routeId)
    }
}