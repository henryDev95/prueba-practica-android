package com.henrymoya.amobaapp.domain

import com.henrymoya.amobaapp.data.network.model.dog.DogResponse
import com.henrymoya.amobaapp.data.network.repository.DogRepository

class GetDogsByRaceUseCase {
    private val dogRepository = DogRepository()
    suspend fun getAllDogByRace(race: String): DogResponse {
        return dogRepository.getAllDogsByRace(race)
    }
}