package com.buigues.ortola.touristics.model.entity

import androidx.room.Embedded
import androidx.room.Relation

data class RouteWithPointsOfInterest(
    @Embedded val route: Route,
    @Relation(
        parentColumn = "id",
        entityColumn = "route_id"
    ) val pointsOfInterest: List<PointOfInterest>
)
