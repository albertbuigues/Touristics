package com.buigues.ortola.touristics.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.buigues.ortola.touristics.model.dao.RouteDao
import com.buigues.ortola.touristics.model.database.AppDatabase
import com.buigues.ortola.touristics.model.entity.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoutesListViewModel(application: Application): AndroidViewModel(application)
{
    private val routeDao: RouteDao = AppDatabase.getInstance(application).routeDao

    fun getAllRoutes(): List<Route> {
        var routesList = emptyList<Route>()
        CoroutineScope(Dispatchers.IO).launch { routesList = routeDao.getAllRoutes() }
        return routesList
    }
}