package com.henrymoya.amobaapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henrymoya.amobaapp.data.network.model.dog.DogResponse
import com.henrymoya.amobaapp.domain.GetDogsByRaceUseCase
import kotlinx.coroutines.launch

class DogViewModel:ViewModel(){
    var getDogsByRaceUseCase = GetDogsByRaceUseCase()
    var dogsList = MutableLiveData<DogResponse>(DogResponse(emptyList()))
    val isShaimmer = MutableLiveData<Boolean>(true)
    fun getDogByRace(race : String){
        viewModelScope.launch {
            isShaimmer.value = true
            val response = getDogsByRaceUseCase.getAllDogByRace(race)
            if(response.images.isNotEmpty()){
                dogsList.value = response
            }
            isShaimmer.value = false
        }
    }
}