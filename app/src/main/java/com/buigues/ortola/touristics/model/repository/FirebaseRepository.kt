package com.buigues.ortola.touristics.model.repository

import com.buigues.ortola.touristics.model.dao.RouteDao
import com.buigues.ortola.touristics.model.entity.PointOfInterest
import com.buigues.ortola.touristics.model.entity.Route
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FirebaseRepository @Inject constructor(private val routeDao: RouteDao)
{
    private var counter = 0
    private val listOfPoints: MutableList<PointOfInterest> = mutableListOf()

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
            CoroutineScope(Dispatchers.IO).launch {
                routeDao.insertRoute(route)
            }
            getRoutePoints(id)
        }
    }

    private fun getRoutePoints(routeId: String) {
        val dbReference = FirebaseDatabase.getInstance().getReference("/routes/$routeId")
        dbReference.child("pointsOfInterest").get().addOnSuccessListener { pointsArray ->
            pointsArray.children.forEach { point ->
                val dataMap = point.value as Map<*, *>
                val routeIdToInsert = routeId.toInt()
                val id = counter
                counter++
                val pointOfInterest = PointOfInterest(
                id = id,
                title = dataMap["title"].toString(),
                description = dataMap["description"].toString(),
                latitude = dataMap["latitude"].toString().toDouble(),
                longitude = dataMap["longitude"].toString().toDouble(),
                imageUrl = dataMap["imageUrl"].toString(),
                routeId = routeIdToInsert
                )
                listOfPoints.add(id, pointOfInterest)
                if (pointsArray.children.count() == listOfPoints.size) {
                    CoroutineScope(Dispatchers.IO).launch {
                        routeDao.insertRoutePoints(listOfPoints)
                    }
                }
            }
        }
    }
}