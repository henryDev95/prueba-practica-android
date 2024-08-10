package com.henrymoya.amobaapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.henrymoya.amobaapp.core.model.DogRace
import com.henrymoya.amobaapp.databinding.ItemDogRaceBinding

class RaceDogAdapter(
    val listRaceDog: List<DogRace>,
    private val itemSelector: (DogRace) -> Unit
) : RecyclerView.Adapter<RaceDogAdapter.RaceDpgViewHolder>() {

    class RaceDpgViewHolder(
        private var binding: ItemDogRaceBinding,
        var itemSelector: (DogRace) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(raceDog: DogRace) {
            binding.apply {
                binding.nameRaceDog.text = raceDog.race
                this.btList.setOnClickListener {
                    itemSelector(raceDog)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaceDpgViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RaceDpgViewHolder(
            ItemDogRaceBinding.inflate(layoutInflater, parent, false),
            itemSelector
        )
    }

    override fun getItemCount(): Int {
        return listRaceDog.size
    }

    override fun onBindViewHolder(holder: RaceDpgViewHolder, position: Int) {
        holder.bind(listRaceDog[position])
    }
}