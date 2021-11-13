package com.buigues.ortola.touristics.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.buigues.ortola.touristics.model.entity.PointOfInterest

@Dao
interface PointsDao
{
    @Query("SELECT * FROM points_tbl WHERE route_id = :routeId")
    suspend fun getRoutePoints(routeId: Int): List<PointOfInterest>
}