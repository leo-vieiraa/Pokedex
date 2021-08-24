package com.example.pokedex.services

import com.example.pokedex.model.PokeResponse
import com.example.pokedex.model.PokemonDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeService {

    @GET("/api/v2/pokemon/")
    fun getAll(): Call<PokeResponse>

    @GET("/api/v2/pokemon/{id}")
    fun getDetails(@Path("id") id: String): Call<PokemonDetails>

}