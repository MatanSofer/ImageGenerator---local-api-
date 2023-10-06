package com.example.restapi_ktorbackend.data

import retrofit2.http.GET

interface RabbitsApi {

    @GET("/randomrabbit")
    suspend fun getRandomRabbit(): Rabbit

    companion object {
        const val  BASE_URL = "http://192.168.1.102:8100"
    }
}