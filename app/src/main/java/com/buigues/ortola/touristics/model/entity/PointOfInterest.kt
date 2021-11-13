package com.buigues.ortola.touristics.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.NO_ACTION
import androidx.room.PrimaryKey

@Entity(
    tableName = "points_tbl",
    foreignKeys = [
    ForeignKey(
        entity = Route::class,
        parentColumns = ["id"],
        childColumns = ["route_id"],
        onDelete = NO_ACTION,
        onUpdate = NO_ACTION
    )])
data class PointOfInterest(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "route_id") val routeId: Int
)
