package com.henrymoya.amobaapp.data.network.service

import com.henrymoya.amobaapp.BuildConfig.API_KEY
import com.henrymoya.amobaapp.core.RetrofitHelper
import com.henrymoya.amobaapp.data.network.apiclient.WebApiService
import com.henrymoya.amobaapp.data.network.model.dog.DogResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogService {
    private val retrofit = RetrofitHelper.getRetrofit(API_KEY)
    suspend fun getDogByRace(race:String ): DogResponse {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(WebApiService::class.java).getDogsByBreeds(race)
            if(response.isSuccessful){
                response.body() ?:  DogResponse(emptyList())
            }else{
                DogResponse(emptyList())
            }
        }
    }
}