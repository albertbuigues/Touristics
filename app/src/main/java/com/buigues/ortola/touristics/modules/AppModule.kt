package com.buigues.ortola.touristics.modules

import android.content.Context
import androidx.room.Room
import com.buigues.ortola.touristics.model.dao.PointsDao
import com.buigues.ortola.touristics.model.dao.RouteDao
import com.buigues.ortola.touristics.model.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule
{
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "touristicsDb"
        )
            .createFromAsset("database/touristicsDb.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRoutesDao(db: AppDatabase): RouteDao = db.routeDao

    @Singleton
    @Provides
    fun providePointsDao(db: AppDatabase): PointsDao = db.pointsDao
}