package com.example.pokedex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.model.Pokemon

class PokemonAdapter() : RecyclerView.Adapter<PokemonViewHolder>() {

    private val pokemonList = mutableListOf<Pokemon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_card_item, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        pokemonList[position].apply {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int  = pokemonList.size

    fun update(newlist: List<Pokemon>) {
        pokemonList.clear()
        pokemonList.addAll(newlist)
        notifyDataSetChanged()
    }

}

class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(pokemon: Pokemon) {

        itemView.findViewById<TextView>(R.id.nameTextView).text = pokemon.name
        itemView.findViewById<TextView>(R.id.urlTextView).text = pokemon.url

    }

}