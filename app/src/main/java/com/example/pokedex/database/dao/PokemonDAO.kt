package com.example.pokedex.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.Types

@Dao
interface PokemonDAO {

    @Query("SELECT * FROM Pokemon ORDER BY poke_name")
    fun all(): List<Pokemon>

    @Query("SELECT * FROM Pokemon WHERE poke_name = :pokeId")
    fun byId(pokeId: String): Pokemon?

    @Insert
    fun insert(pokemon: Pokemon)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertType(types: List<Types>)

    @Query("SELECT * FROM pokemon WHERE poke_name LIKE '%' || :query || '%'")
    fun fetchFiltered(query: String): List<Pokemon>

}