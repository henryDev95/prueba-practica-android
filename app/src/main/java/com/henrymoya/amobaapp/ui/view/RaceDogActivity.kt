package com.henrymoya.amobaapp.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.henrymoya.amobaapp.core.adapter.DogAdapter
import com.henrymoya.amobaapp.databinding.ActivityRaceDogBinding
import com.henrymoya.amobaapp.ui.viewmodel.DogViewModel

class RaceDogActivity : AppCompatActivity() {
    lateinit var binding: ActivityRaceDogBinding
    private val dogViewModel: DogViewModel by viewModels()
    lateinit var  dogAdapter:DogAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val race = intent.getStringExtra("race")
        binding = ActivityRaceDogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val layoutManager = LinearLayoutManager(this)
        binding.dogRaceList.layoutManager = layoutManager

        dogViewModel.dogsList.observe(this, Observer {
            if(it.images.isNotEmpty()){
                binding.dogRaceList.isVisible = true
                dogAdapter = DogAdapter(it)
                binding.dogRaceList.adapter = dogAdapter
            }
        })

        dogViewModel.getDogByRace(race?.lowercase() ?:"")

        dogViewModel.isShaimmer.observe(this, Observer {
            binding.viewDogList.isVisible= it
        })
    }
}