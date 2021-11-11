package com.buigues.ortola.touristics.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.buigues.ortola.touristics.model.entity.PointOfInterest

@Dao
interface PointsDao
{
    @Query("SELECT * FROM points_tbl WHERE route_id = :routeId")
    fun getRoutePoints(routeId: Int): LiveData<List<PointOfInterest>>
}