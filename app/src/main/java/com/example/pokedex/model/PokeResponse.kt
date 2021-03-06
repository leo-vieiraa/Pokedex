package com.example.pokedex.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class PokeResponse(
    val results: List<Pokemon>
)

@Entity
data class Pokemon(
    @PrimaryKey
    @ColumnInfo(name = "poke_name")
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "poke_url")
    @SerializedName("url")
    val url: String,

    @Embedded
    var details: PokemonDetails
) {
    fun extractIdFromUrl(): String {
        val listStr = url.split("/")
        return listStr[6]
    }
}
