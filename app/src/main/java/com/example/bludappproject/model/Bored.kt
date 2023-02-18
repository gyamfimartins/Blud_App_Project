package com.gyamfimartins.neverbored.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

data class Bored(
    var activity: String,
    var accessibility: Double,
    var type: String,
    var participants: Int,
    var price: Double,
    var link: String,
    var key: String
)

@Entity(tableName = "activty_table", indices = [Index(value = ["key"], unique = true)])
data class SavedActivity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var activity: String,
    var accessibility: String,
    var type: String,
    var participants: String,
    var price: String,
    var link: String,
    var key: String
)
