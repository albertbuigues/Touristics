package com.buigues.ortola.touristics.repository

import android.app.Application
import com.buigues.ortola.touristics.TouristicsApp
import com.buigues.ortola.touristics.model.dao.RouteDao
import com.buigues.ortola.touristics.model.database.AppDatabase
import com.buigues.ortola.touristics.model.entity.Route
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRepository @Inject constructor(val application: Application)
{
    fun dumpDataFromFirebase() {
        getRouteFromId("0")
        getRouteFromId("1")
    }

    // Gets a Route and inserts into Room
    private fun getRouteFromId(id: String) {
        val dbReference = FirebaseDatabase.getInstance().getReference("/routes")
        dbReference.child(id).get().addOnSuccessListener {
            val dataMap = it.value as Map<*, *>
            val routeId = id.toInt()
            val route = Route(
                id = routeId,
                title = dataMap["title"].toString(),
                description = dataMap["description"].toString(),
                historicPeriod = dataMap["historicPeriod"].toString(),
                imageUrl = dataMap["imageUrl"].toString()
            )
            AppDatabase.getInstance(application).routeDao.insertRoute(route)
        }
    }
}