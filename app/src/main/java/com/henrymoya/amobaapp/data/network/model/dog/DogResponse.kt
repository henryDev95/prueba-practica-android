package com.henrymoya.amobaapp.data.network.model.dog

import com.google.gson.annotations.SerializedName

data class DogResponse(
    @SerializedName("message") var images: List<String>
)
