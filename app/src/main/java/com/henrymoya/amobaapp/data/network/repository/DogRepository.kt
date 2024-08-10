package com.henrymoya.amobaapp.data.network.repository

import com.henrymoya.amobaapp.data.network.model.dog.DogResponse
import com.henrymoya.amobaapp.data.network.service.DogService

class DogRepository {
    private val api = DogService()
    suspend fun getAllDogsByRace(race:String): DogResponse {
        val response = api.getDogByRace(race)
        return response
    }
}