package com.buigues.ortola.touristics.model.entity

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "points_tbl",
    foreignKeys = [
    ForeignKey(
        entity = Route::class,
        parentColumns = ["id"],
        childColumns = ["route_id"],
        onDelete = CASCADE,
        onUpdate = CASCADE
    )],
    indices = [
        Index(
            value = ["route_id"],
            name = "route_id_routes",
            unique = true)
    ])
data class PointOfInterest(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "route_id") val routeId: Int
)
