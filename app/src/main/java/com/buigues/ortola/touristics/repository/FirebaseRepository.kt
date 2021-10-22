package com.buigues.ortola.touristics.repository

import android.app.Application
import com.buigues.ortola.touristics.model.database.AppDatabase
import com.buigues.ortola.touristics.model.entity.PointOfInterest
import com.buigues.ortola.touristics.model.entity.Route
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRepository @Inject constructor(private val application: Application)
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
            getRoutePoints(route,"0")
            getRoutePoints( route,"1")
            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getInstance(application).routeDao.insertRoute(route)
            }
        }
    }

    private fun getRoutePoints(route: Route, routeId: String) {
        val dbReference = FirebaseDatabase.getInstance().getReference("/routes/$routeId")
        dbReference.child("pointsOfInterest").get().addOnSuccessListener { pointsArray ->
            pointsArray.children.forEach { point ->
                val dataMap = point.value as Map<*, *>
                val routeIdToInsert = routeId.toInt()
                val id = point.key!!.toInt()
                val pointOfInterest = PointOfInterest(
                    id = id,
                    title = dataMap["title"].toString(),
                    description = dataMap["description"].toString(),
                    latitude = dataMap["latitude"].toString().toDouble(),
                    longitude = dataMap["longitude"].toString().toDouble(),
                    imageUrl = dataMap["imageUrl"].toString(),
                    routeId = routeIdToInsert
                )
                CoroutineScope(Dispatchers.IO).launch {
                    AppDatabase.getInstance(application).pointDao.insertPointByRoute(route, pointOfInterest)
                }
            }
        }
    }
}