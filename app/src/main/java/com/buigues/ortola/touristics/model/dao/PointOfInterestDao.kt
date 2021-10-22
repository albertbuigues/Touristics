package com.buigues.ortola.touristics.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import com.buigues.ortola.touristics.model.entity.PointOfInterest
import com.buigues.ortola.touristics.model.entity.Route

@Dao
interface PointOfInterestDao {

    @Insert(onConflict = IGNORE)
    suspend fun insertPointByRoute(route: Route, point: PointOfInterest)
}