package com.henrymoya.amobaapp.data.network.apiclient

import com.henrymoya.amobaapp.data.network.model.dog.DogResponse
import com.henrymoya.amobaapp.data.network.model.user.UserRequest
import com.henrymoya.amobaapp.data.network.model.user.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface WebApiService {
    @GET("{race}/images")
    suspend fun getDogsByBreeds(
        @Path("race") race: String
    ):Response<DogResponse>
}