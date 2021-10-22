package com.buigues.ortola.touristics.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.buigues.ortola.touristics.model.entity.PointOfInterest
import com.buigues.ortola.touristics.model.entity.Route

@Dao
interface RouteDao
{
    @Insert(onConflict = REPLACE)
    suspend fun insertRoute(route: Route)

    @Query("SELECT * FROM routes_tbl")
    fun getAllRoutes(): List<Route>

    @Transaction
    @Insert(onConflict = REPLACE)
    fun insertRoutePoints(points: List<PointOfInterest>)
}