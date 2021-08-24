package com.example.pokedex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.databinding.PokemonCardItemBinding
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

    private val binding: PokemonCardItemBinding = PokemonCardItemBinding.bind(view)

    fun bind(pokemon: Pokemon) {

        binding.idTextView.text = "#${pokemon.extractIdFromUrl()}"
        binding.nameTextView.text = pokemon.name

        itemView.findViewById<TextView>(R.id.nameTextView).text = pokemon.name
        pokemon.details?.let {
            Glide.with(itemView.context)
                .load(it.sprites.other.artWork?.image)
                .into(binding.avatarImageView)
        }
        pokemon.details?.let {
            val bgColor = it.type[0].type.extractBgColor()
            binding.cardItem.setCardBackgroundColor(itemView.context.getColor(bgColor))
        }

    }

}