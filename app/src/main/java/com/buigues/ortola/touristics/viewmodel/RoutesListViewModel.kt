package com.buigues.ortola.touristics.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.buigues.ortola.touristics.model.dao.RouteDao
import com.buigues.ortola.touristics.model.database.AppDatabase
import com.buigues.ortola.touristics.model.entity.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoutesListViewModel(application: Application): AndroidViewModel(application)
{
    @Inject var routeDao: RouteDao = AppDatabase.getInstance(application).routeDao


    suspend fun getAllRoutes(): List<Route> {
        return routeDao.getAllRoutes()
    }
}