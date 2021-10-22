package com.buigues.ortola.touristics.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routes_tbl")
data class Route(
    @PrimaryKey(autoGenerate = false) private val id: Int,
    @ColumnInfo(name = "title") private val title: String,
    @ColumnInfo(name = "description") private val description: String,
    @ColumnInfo(name = "historic_period") private val historicPeriod: String,
    @ColumnInfo(name = "image_url") private val imageUrl: String
)
