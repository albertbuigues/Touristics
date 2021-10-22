package com.buigues.ortola.touristics.repository

import androidx.room.Insert
import com.buigues.ortola.touristics.model.dao.RouteDao
import com.buigues.ortola.touristics.model.entity.Route
import javax.inject.Singleton

@Singleton
class LocalRepository(private val routeDao: RouteDao)
{
    @Insert
    fun insertRoute(route: Route)
    {
        routeDao.insertRoute(route)
    }
}