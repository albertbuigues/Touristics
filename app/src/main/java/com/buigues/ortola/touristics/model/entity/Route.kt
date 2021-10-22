package com.buigues.ortola.touristics.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routes_tbl")
data class Route(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "historic_period") val historicPeriod: String,
    @ColumnInfo(name = "image_url") val imageUrl: String
)
