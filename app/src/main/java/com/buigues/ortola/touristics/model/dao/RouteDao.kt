package com.buigues.ortola.touristics.model.dao

import androidx.room.Dao
import androidx.room.Insert
import com.buigues.ortola.touristics.model.entity.Route

@Dao
interface RouteDao
{
    @Insert
    fun insertRoute(route: Route)
}