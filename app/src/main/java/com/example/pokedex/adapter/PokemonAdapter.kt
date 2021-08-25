package com.example.pokedex.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.databinding.PokemonCardItemBinding
import com.example.pokedex.model.Pokemon
import com.example.pokedex.utils.toUpperFirstChar

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

    @SuppressLint("ResourceType")
    fun bind(pokemon: Pokemon) {

        binding.idTextView.text = "#${pokemon.extractIdFromUrl()}"
        binding.nameTextView.text = pokemon.name

        itemView.findViewById<TextView>(R.id.nameTextView).text = pokemon.name
        pokemon.details?.let {
            Glide.with(itemView.context)
                .load(it.sprites.other.artWork?.image)
                .into(binding.avatarImageView)

            val pokeTypeSetup = it.type[0].type.extractPokeSetup()
            binding.cardItem.setCardBackgroundColor(itemView.context.getColor(pokeTypeSetup.colorCard))
            binding.typesContainer.typeCardView1.setCardBackgroundColor(
                itemView.context.getColor(
                    pokeTypeSetup.colorType
                )
            )
            binding.typesContainer.typeImageView1.setImageDrawable(
                itemView.context.getDrawable(
                    pokeTypeSetup.icon
                )
            )
            binding.typesContainer.typeTextView1.text = it.type[0].type.typeName.toUpperFirstChar()

            if (it.type.size > 1) {
                val setupCard2 = it.type[1].type.extractPokeSetup()
                binding.typesContainer.typeCardView2.setCardBackgroundColor(
                    itemView.context.getColor(
                        setupCard2.colorType
                    )
                )
                binding.typesContainer.typeImageView2.setImageDrawable(
                    itemView.context.getDrawable(
                        setupCard2.icon
                    )
                )
                binding.typesContainer.typeTextView2.text =
                    it.type[1].type.typeName.toUpperFirstChar()
                binding.typesContainer.typeCardView2.visibility = View.VISIBLE
            } else {
                binding.typesContainer.typeCardView2.visibility = View.GONE
            }
        }

    }

}