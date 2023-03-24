package com.example.superheroapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SuperheroAdapter(
    var superheroList: List<ResultsItemResponse> = emptyList(),
    private val onItemSelected: (String) -> Unit
) :
    RecyclerView.Adapter<SuperHeroViewHolder>() {

    fun updateList(superheroList: List<ResultsItemResponse>) {
        this.superheroList = superheroList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun onBindViewHolder(viewholder: SuperHeroViewHolder, position: Int) {
        viewholder.bind(superheroList[position],onItemSelected)


    }

    override fun getItemCount() = superheroList.size

}