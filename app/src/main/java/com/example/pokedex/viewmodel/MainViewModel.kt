package com.example.pokedex.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.model.Pokemon
import com.example.pokedex.repository.PokemonRepository

class MainViewModel : ViewModel() {

    val _pokeResponse = MutableLiveData<List<Pokemon>>()
    val pokeResponse : LiveData<List<Pokemon>> = _pokeResponse

    val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private fun fetchAllFromServer(context: Context) {
        val repository = PokemonRepository(context)

        repository.fetchAll { response, error ->
            response?.let {
                _pokeResponse.value = it.results
                loadPokeDetails(it.results, repository)
            }
            error?.let {
                _error.value = it
            }
        }
    }

    private fun loadPokeDetails(pokemons: List<Pokemon>, repository: PokemonRepository) {
        pokemons.forEach { poke ->
            repository.fetchPokemonDetails(pokeId = poke.extractIdFromUrl()) { details, _ ->
                details?.let {

                    poke.details = details
                    repository.insertIntoDatabase(poke)

                }
            }
        }
    }

    fun fetchAllFromDatabase(context: Context) {
        val listOf = PokemonRepository(context).fetchAllFromDatabase()
        if (listOf != null && listOf.isNotEmpty()) {
            _pokeResponse.value = listOf!!
        } else {
            fetchAllFromServer(context)
        }

    }

    fun fetchFilteredFromDatabase(context: Context, query: String) {
        val repository = PokemonRepository(context)
        val filteredList = repository.fetchAllFromDatabaseWithFilter(query)
        filteredList?.let {
            _pokeResponse.value = it
        }
    }

}