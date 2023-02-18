package com.gyamfimartins.neverbored.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BoredService {
    private val BASE_URL = "https://www.boredapi.com"
    fun getBoredService(): BoredApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BoredApi::class.java)
    }
}