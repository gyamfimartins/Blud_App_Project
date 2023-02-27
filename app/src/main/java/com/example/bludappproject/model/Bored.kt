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

data class SavedActivity(
    var activityId: String,
    var activity: String,
    var status: String
)
