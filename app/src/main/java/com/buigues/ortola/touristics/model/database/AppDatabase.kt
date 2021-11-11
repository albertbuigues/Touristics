package com.buigues.ortola.touristics.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.buigues.ortola.touristics.model.dao.PointsDao
import com.buigues.ortola.touristics.model.dao.RouteDao
import com.buigues.ortola.touristics.model.entity.PointOfInterest
import com.buigues.ortola.touristics.model.entity.Route

@Database(entities = [Route::class, PointOfInterest::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase()
{
    abstract val routeDao: RouteDao
    abstract val pointsDao: PointsDao
}