package com.gyamfimartins.neverbored.network

import com.gyamfimartins.neverbored.model.Bored
import retrofit2.http.GET
import retrofit2.http.Query

interface BoredApi {
    @GET("/api/activity/")
    suspend fun getRandomActivity(): Bored

    @GET("/api/activity")
    suspend fun getByActivityType(@Query("type") type: String): Bored

    @GET("/api/activity")
    suspend fun getByParticipants(@Query("participants") type: String): Bored
}