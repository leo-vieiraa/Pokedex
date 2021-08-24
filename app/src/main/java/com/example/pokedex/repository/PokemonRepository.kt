package com.example.pokedex.repository

import android.content.Context
import com.example.pokedex.database.AppDatabase
import com.example.pokedex.model.PokeResponse
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonDetails
import com.example.pokedex.services.RefrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepository (private val context: Context) {

    private val database = AppDatabase.getDatabase(context)
    val service = RefrofitService.getPokeService()

    fun fetchAll(onComplete: (PokeResponse?, String?) -> Unit) {
        val call = service.getAll()
        call.enqueue(object : Callback<PokeResponse> {
            override fun onResponse(call: Call<PokeResponse>, response: Response<PokeResponse>) {
                if (response.body() != null) {
                    onComplete(response.body(), null)
                } else {
                    onComplete(null, "Nenhum pokemon encontrado")
                }
            }

            override fun onFailure(call: Call<PokeResponse>, t: Throwable) {
                onComplete(null, t.message)
            }
        })
    }

    fun fetchPokemonDetails(pokeId: String, onComplete: (PokemonDetails?, String?) -> Unit) {
        val call = service.getDetails(pokeId)
        call.enqueue(object : Callback<PokemonDetails> {
            override fun onResponse(
                call: Call<PokemonDetails>,
                response: Response<PokemonDetails>
            ) {
                if (response.body() != null) {
                    onComplete(response.body(), null)
                } else {
                    onComplete(null, "Nenhum pokemon encontrado")
                }
            }

            override fun onFailure(call: Call<PokemonDetails>, t: Throwable) {
                onComplete(null, t.message)
            }
        })
    }


    fun insertIntoDatabase(items: List<Pokemon>) {
        val dao = database.pokemonDAO()
        items.forEach { poke ->
            dao.insert(pokemon = poke)
        }

    }

    fun insertIntoDatabase(pokemon: Pokemon) {
        val dao = database.pokemonDAO()
        dao.insert(pokemon)
    }

    fun fetchAllFromDatabase(): List<Pokemon>? {
        val dao = database.pokemonDAO()
        return dao.all()
    }

}