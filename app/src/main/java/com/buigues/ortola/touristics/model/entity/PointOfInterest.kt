package com.buigues.ortola.touristics.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "points_tbl", foreignKeys = [
    ForeignKey(
        entity = Route::class,
        parentColumns = ["id"],
        childColumns = ["route_id"],
        onDelete = CASCADE,
        onUpdate = CASCADE
    )])
data class PointOfInterest(
    @PrimaryKey(autoGenerate = false) private val id: Int,
    @ColumnInfo(name = "title") private val title: String,
    @ColumnInfo(name = "description") private val description: String,
    @ColumnInfo(name = "latitude") private val latitude: Double,
    @ColumnInfo(name = "longitude") private val longitude: Double,
    @ColumnInfo(name = "image_url") private val imageUrl: String,
    @ColumnInfo(name = "route_id") private val routeId: Int
)
