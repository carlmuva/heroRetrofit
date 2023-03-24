package com.example.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapp.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view:View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(resultsItemResponse: ResultsItemResponse , onItemSelected: (String) -> Unit){
        binding.tvSuperheroName.text = resultsItemResponse.name
        Picasso.get().load(resultsItemResponse.image.url).into(binding.ivSuperhero)
        binding.root.setOnClickListener { onItemSelected(resultsItemResponse.id) }


    }


}