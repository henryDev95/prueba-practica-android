package com.henrymoya.amobaapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.henrymoya.amobaapp.data.network.model.dog.DogResponse
import com.henrymoya.amobaapp.databinding.ItemDogsBinding
import com.squareup.picasso.Picasso

class DogAdapter(
    val listDog: DogResponse
) : RecyclerView.Adapter<DogAdapter.DogViewHolder>() {

    class DogViewHolder(
        private var binding: ItemDogsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: String) {
            Picasso.get().load(image).into(binding.ivDog)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DogViewHolder(
            ItemDogsBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listDog.images.size
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(listDog.images[position])
    }
}